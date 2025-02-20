package com.backend.user.controller;

import com.backend.user.dto.UserRequest;
import com.backend.user.dto.UserResponse;
import com.backend.user.serialization.ApiResponse;
import com.backend.user.serialization.Meta;
import com.backend.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.time.LocalDateTime.now;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/user-api/v1/backoffice")
public class BackofficeController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getListEmployee(@RequestParam String agency){
        List<UserResponse> userResponse;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        if (role.equals("ROLE_ADMIN")) {
             userResponse = userService.getAllEmployees(agency);
        } else {
             userResponse = userService.getAllAgents(agency);
        }

        Meta meta = new Meta(now(), "v1");
        String status = "user retrieved";
        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>(status,userResponse,meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(@RequestBody @Valid UserRequest request){
        UserResponse userResponse = userService.registerUser(request);

        Meta meta = new Meta(now(), "v1");
        String status = "user saved";
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>(status,userResponse,meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
