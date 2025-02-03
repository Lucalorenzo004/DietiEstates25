package com.backend.user.service;

import com.backend.user.dto.UserRequest;
import com.backend.user.dto.UserResponse;


public interface UserService {
    UserResponse getUser(Long userId);
    UserResponse registerUser(UserRequest request);
    UserResponse updateUser(UserRequest request);
    Boolean deleteUser(Long userId);

}
