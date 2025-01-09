package com.backend.user.controller;

import com.backend.user.dto.UserRequestDTO;
import com.backend.user.model.Response;
import com.backend.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user-api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<Response> getUser(@PathVariable Long userId){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("user",userService.getUser(userId)))
                        .message("user retrieved")
                        .httpStatus(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<Response> registerUser(@RequestBody @Valid UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("user",userService.registerUser(userRequestDTO)))
                        .message("user saved")
                        .httpStatus(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @PutMapping
    public ResponseEntity<Response> updateUser(@RequestBody @Valid UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("user",userService.updateUser(userRequestDTO)))
                        .message("user updated")
                        .httpStatus(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping
    public ResponseEntity<Response> deleteUser(@RequestParam(value = "user") Long userId){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("user",userService.deleteUser(userId)))
                        .message("user deleted")
                        .httpStatus(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

}
