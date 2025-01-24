package com.backend.user.service;

import com.backend.user.dto.UserRequestDTO;
import com.backend.user.dto.UserResponseDTO;


public interface UserService {
    UserResponseDTO getUser(Long userId);
    UserResponseDTO registerUser(UserRequestDTO request);
    UserResponseDTO updateUser(UserRequestDTO request);
    Boolean deleteUser(Long userId);

}
