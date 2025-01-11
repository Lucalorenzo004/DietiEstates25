package com.backend.user.service.implementation;

import com.backend.user.dto.UserRequestDTO;
import com.backend.user.dto.UserResponseDTO;
import com.backend.user.model.Role;
import com.backend.user.model.User;
import com.backend.user.repository.UserRepository;
import com.backend.user.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
    @Override
    public UserResponseDTO getUser(Long userId) {
        logger.info("Fetching user by id: {}",userId);

        Optional<User> optional = userRepository.findById(userId);
        if (optional.isEmpty()){
            throw new NotFoundException();
        }

        User user = optional.get();
        return mapEntityToDTO(user);
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO request) {
        logger.info("Saving new user {}", request);

        User user = mapDTOToEntity(request);
        user = userRepository.save(user);

        return mapEntityToDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(UserRequestDTO request) {
        logger.info("Updating the user {}",request);

        User user = mapDTOToEntity(request);

        return mapEntityToDTO(userRepository.save(user));
    }

    @Override
    public Boolean deleteUser(Long userId) {
        logger.info("Deleting user by ID: {}", userId);
        userRepository.deleteById(userId);

        return TRUE;
    }

    private UserResponseDTO mapEntityToDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .role(String.valueOf(user.getRole()))
                .build();
    }

    private User mapDTOToEntity(UserRequestDTO request) {
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
