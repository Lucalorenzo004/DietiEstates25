package com.backend.user.service;

import com.backend.user.dto.UserRequest;
import com.backend.user.dto.UserResponse;

import java.util.List;


public interface UserService {
    UserResponse getUser(Long userId);
    UserResponse registerUser(UserRequest request);
    UserResponse updateUser(UserRequest request);
    void deleteUser(Long userId);
    List<UserResponse> getAllEmployees(String agency);
    List<UserResponse> getAllAgents(String agency);

}
