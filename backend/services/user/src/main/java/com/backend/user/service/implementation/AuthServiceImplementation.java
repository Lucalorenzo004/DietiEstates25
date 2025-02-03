package com.backend.user.service.implementation;

import com.backend.user.dto.AuthRequest;
import com.backend.user.dto.AuthResponse;
import com.backend.user.dto.UserRequest;
import com.backend.user.dto.UserResponse;
import com.backend.user.model.Role;
import com.backend.user.model.User;
import com.backend.user.repository.UserRepository;
import com.backend.user.security.JwtService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserResponse register(UserRequest request) {
        var user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .provider(request.getProvider())
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

    private Map<String,Object> setExtraClaims(User user){
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", user.getId());
        extraClaims.put("role","ROLE_"+user.getRole());

        return extraClaims;
    }

}
