package com.backend.user.service.implementation;

import com.backend.user.dto.UserRequest;
import com.backend.user.dto.UserResponse;
import com.backend.user.model.Role;
import com.backend.user.model.User;
import com.backend.user.repository.UserRepository;
import com.backend.user.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserResponse getUser(Long userId) {
        log.info("Fetching user by id: {}",userId);

        Optional<User> optional = userRepository.findById(userId);
        if (optional.isEmpty()){
            throw new NotFoundException();
        }

        User user = optional.get();
        return mapEntityToDTO(user);
    }

    @Override
    public UserResponse registerUser(UserRequest request) {
        log.info("Saving new user {}", request);

        User user = mapDTOToEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        return mapEntityToDTO(user);
    }

    @Override
    public UserResponse updateUser(UserRequest request) {
        log.info("Updating the user {}",request);

        User user = mapDTOToEntity(request);

        return mapEntityToDTO(userRepository.save(user));
    }

    @Override
    public Boolean deleteUser(Long userId) {
        log.info("Deleting user by ID: {}", userId);
        userRepository.deleteById(userId);

        return TRUE;
    }

    private UserResponse mapEntityToDTO(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .role(String.valueOf(user.getRole()))
                .build();
    }

    private User mapDTOToEntity(UserRequest request) {
        return User.builder()
                .id(request.getId())
                .email(request.getEmail())
                .name(request.getName())
                .surname(request.getSurname())
                .provider(request.getProvider())
                .password(request.getPassword())
                .role(Role.valueOf(request.getRole()))
                .build();
    }

}
