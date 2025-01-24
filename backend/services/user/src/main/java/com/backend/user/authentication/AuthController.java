package com.backend.user.authentication;

import com.backend.user.dto.UserRequestDTO;
import com.backend.user.dto.UserResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/user-api/v1/auth")
public class AuthController{

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(
            @RequestBody UserRequestDTO request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
}
