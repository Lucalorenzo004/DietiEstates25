package com.backend.user.controller;

import com.backend.user.service.implementation.AuthServiceImplementation;
import com.backend.user.dto.AuthRequest;
import com.backend.user.dto.UserRequest;
import com.backend.user.serialization.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/user-api/v1/auth")
public class AuthController{

    private final AuthServiceImplementation service;

    @PostMapping("/login")
    public ResponseEntity<Response> authenticate(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(Response.builder()
                .timestamp(LocalDateTime.now())
                .data(Map.of("login",service.authenticate(request)))
                .message("user logged")
                .httpStatus(OK)
                .statusCode(OK.value())
                .build()
        );
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(Response.builder()
                .timestamp(LocalDateTime.now())
                .data(Map.of("registration",service.register(request)))
                .message("user registered")
                .httpStatus(CREATED)
                .statusCode(CREATED.value())
                .build()
        );
    }
}
