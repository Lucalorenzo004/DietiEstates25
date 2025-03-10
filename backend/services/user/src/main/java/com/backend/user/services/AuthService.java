package com.backend.user.services;

import com.backend.user.dto.AuthRequest;
import com.backend.user.dto.AuthResponse;
import com.backend.user.dto.UserRequest;
import com.backend.user.dto.UserResponse;

import java.util.Map;


public interface AuthService {
    UserResponse register(UserRequest request);
    AuthResponse authenticateGoogle(Map<String, String> request);
    AuthResponse authenticate(AuthRequest request);
}
