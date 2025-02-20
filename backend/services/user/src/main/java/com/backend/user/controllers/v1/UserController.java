package com.backend.user.controllers.v1;

import com.backend.user.dto.UserRequest;
import com.backend.user.dto.UserResponse;
import com.backend.user.serialization.ApiResponse;
import com.backend.user.serialization.Meta;
import com.backend.user.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/user-api/v1/users")
public class UserController {

    private final UserService userService;
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable Long userId){
        UserResponse userResponse = userService.getUser(userId);

        Meta meta = new Meta(now(), "v1");
        String status = "user retrieved";
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>(status,userResponse,meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@RequestBody @Valid UserRequest request){
        UserResponse userResponse = userService.updateUser(request);

        Meta meta = new Meta(now(), "v1");
        String status = "user updated";
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>(status,userResponse,meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteUser(@RequestParam(value = "userId") Long userId){
        userService.deleteUser(userId);

        Meta meta = new Meta(now(), "v1");
        String status = "user deleted";
        ApiResponse<Void> apiResponse = new ApiResponse<>(status,meta);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
