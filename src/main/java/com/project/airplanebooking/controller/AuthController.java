package com.project.airplanebooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.airplanebooking.dto.request.LoginRequest;
import com.project.airplanebooking.dto.request.OtpRequest;
import com.project.airplanebooking.dto.request.OtpVerificationRequest;
import com.project.airplanebooking.dto.request.RegisterRequest;
import com.project.airplanebooking.dto.response.JwtAuthenticationResponse;
import com.project.airplanebooking.dto.response.RegisterResponse;
import com.project.airplanebooking.dto.response.UserResponse;
import com.project.airplanebooking.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        UserResponse response = authService.login(loginRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        RegisterResponse response = authService.register(registerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@Valid @RequestBody OtpRequest request) {
        try {
            authService.sendLoginOtp(request.getEmail());
            return new ResponseEntity<>("OTP đã được gửi đến email của bạn", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<JwtAuthenticationResponse> verifyOtp(@Valid @RequestBody OtpVerificationRequest request) {
        try {
            JwtAuthenticationResponse response = authService.verifyOtpAndLogin(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
