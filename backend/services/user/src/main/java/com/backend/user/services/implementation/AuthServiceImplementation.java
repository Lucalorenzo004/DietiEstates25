package com.backend.user.services.implementation;

import com.backend.user.dto.AuthRequest;
import com.backend.user.dto.AuthResponse;
import com.backend.user.dto.UserRequest;
import com.backend.user.dto.UserResponse;
import com.backend.user.entities.Role;
import com.backend.user.entities.User;
import com.backend.user.repositories.UserRepository;
import com.backend.user.security.GoogleService;
import com.backend.user.security.JwtService;

import com.backend.user.services.AuthService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final GoogleService googleService;

    @Override
    public UserResponse register(UserRequest request) {
        var user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .provider("local")
                .agency(request.getAgency())
                .role(Role.valueOf(request.getRole()))
                .build();
        var savedUser = repository.save(user);
        return UserResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .surname(savedUser.getSurname())
                .email(savedUser.getEmail())
                .role(String.valueOf(savedUser.getRole()))
                .build();
    }

    @Override
    public AuthResponse authenticateGoogle(Map<String, String> request){
        User newUser;
        String token = request.get("token");
        GoogleIdToken.Payload payload = googleService.verifyToken(token);

        String email = payload.getEmail();

        if(!userExists(email)){
            newUser = User.builder()
                    .name((String) payload.get("given_name"))
                    .surname((String) payload.get("family_name"))
                    .email(email)
                    .password(null)
                    .provider("google")
                    .agency(null)
                    .role(Role.valueOf("USER"))
                    .build();
            repository.save(newUser);
        }

        var user = repository.findByEmail(email).orElseThrow();

        Map<String, Object> extraClaims = setExtraClaims(user);
        String jwtToken = jwtService.generateToken(extraClaims,user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        Map<String, Object> extraClaims = setExtraClaims(user);
        var jwtToken = jwtService.generateToken(extraClaims,user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    private boolean userExists(String email){
        var user = repository.findByEmail(email);

        return user.isPresent();
    }

    private Map<String,Object> setExtraClaims(User user){
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", user.getId());
        extraClaims.put("role","ROLE_"+user.getRole());
        extraClaims.put("email",user.getEmail());

        String role = String.valueOf(user.getRole());
        if (role.equals("ADMIN") || role.equals("MANAGER")){
            extraClaims.put("agency",user.getAgency());
        }

        return extraClaims;
    }

}
