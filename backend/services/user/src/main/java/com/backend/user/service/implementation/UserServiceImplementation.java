package com.backend.user.service.implementation;

import com.backend.user.dto.UserRequestDTO;
import com.backend.user.dto.UserResponseDTO;
import com.backend.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImplementation implements UserService {
    @Override
    public UserResponseDTO getUser(Long userId) {
        return null;
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO request) {
        return null;
    }

    @Override
    public UserResponseDTO updateUser(UserRequestDTO request) {
        return null;
    }

    @Override
    public Boolean deleteUser(Long userId) {
        return null;
    }


}
