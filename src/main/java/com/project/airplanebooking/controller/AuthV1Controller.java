package com.project.airplanebooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.airplanebooking.dto.request.LoginRequest;
import com.project.airplanebooking.dto.request.RegisterRequest;
import com.project.airplanebooking.dto.response.RegisterResponse;
import com.project.airplanebooking.dto.response.UserResponse;
import com.project.airplanebooking.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:8080" })
public class AuthV1Controller {

    private final AuthService authService;

    public AuthV1Controller(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        UserResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse response = authService.register(registerRequest);
        return ResponseEntity.ok(response);
    }
}