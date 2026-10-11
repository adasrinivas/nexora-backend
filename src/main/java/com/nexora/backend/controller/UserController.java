package com.nexora.backend.controller;

import com.nexora.backend.dto.UserRequest;
import com.nexora.backend.dto.UserResponse;
import com.nexora.backend.model.User;
import com.nexora.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174",
        "http://localhost:5175"
})
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        UserResponse response = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest request) {
        UserResponse response = userService.loginUser(
                request.getEmail(),
                request.getPassword()
        );
        return ResponseEntity.ok(response);
    }
}
