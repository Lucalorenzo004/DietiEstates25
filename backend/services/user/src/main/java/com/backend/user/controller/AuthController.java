package com.backend.user.controller;

import com.backend.user.dto.AuthResponse;
import com.backend.user.dto.UserResponse;
import com.backend.user.serialization.Meta;
import com.backend.user.service.implementation.AuthServiceImplementation;
import com.backend.user.dto.AuthRequest;
import com.backend.user.dto.UserRequest;
import com.backend.user.serialization.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/user-api/v1/auth")
public class AuthController {

    private final AuthServiceImplementation service;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> authenticate(
            @RequestBody AuthRequest request
    ) {
        AuthResponse authResponse = service.authenticate(request);

        Meta meta = new Meta(now(), "v1");
        String status = "user logged";
        ApiResponse<AuthResponse> apiResponse = new ApiResponse<>(status,authResponse,meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/google")
    public ResponseEntity<ApiResponse<AuthResponse>> authenticateGoogle(
            @RequestBody Map<String, String> request
    ) {
        AuthResponse authResponse = service.authenticateGoogle(request);

        Meta meta = new Meta(now(), "v1");
        String status = "user logged";
        ApiResponse<AuthResponse> apiResponse = new ApiResponse<>(status,authResponse,meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(
            @RequestBody UserRequest request
    ) {
        UserResponse userResponse = service.register(request);

        Meta meta = new Meta(now(), "v1");
        String status = "user registered";
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>(status,userResponse,meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
