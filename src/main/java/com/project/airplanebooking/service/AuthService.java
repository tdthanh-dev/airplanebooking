package com.project.airplanebooking.service;

import com.project.airplanebooking.dto.request.LoginRequest;
import com.project.airplanebooking.dto.request.RegisterRequest;
import com.project.airplanebooking.dto.response.RegisterResponse;
import com.project.airplanebooking.dto.response.UserResponse;
import com.project.airplanebooking.exception.ConflictException;
import com.project.airplanebooking.exception.ResourceNotFoundException;
import com.project.airplanebooking.exception.ValidationException;
import com.project.airplanebooking.model.User;
import com.project.airplanebooking.repository.UserRepository;
import com.project.airplanebooking.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider,
            UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);
        String refreshToken = tokenProvider.generateRefreshToken(authentication);
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", loginRequest.getUsername()));
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setToken(token);
        response.setRefreshToken(refreshToken);
        response.setRole(user.getRole());
        response.setStatus(user.getStatus());
        response.setIsActive(user.getIsActive());
        response.setLastLogin(user.getLastLogin());
        response.setFacebookAccountId(user.getFacebookAccountId());
        response.setGoogleAccountId(user.getGoogleAccountId());

        return response;
    }

    public RegisterResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new ConflictException("User", "username", registerRequest.getUsername());
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new ConflictException("User", "email", registerRequest.getEmail());
        }

        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new ValidationException("Password and confirm password do not match!");
        }

        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setPhone(registerRequest.getPhone());
        user.setRole("USER");
        user.setStatus("ACTIVE");
        user.setIsActive(true);
        user.setLastLogin(LocalDateTime.now());
        user.setFacebookAccountId(registerRequest.getFacebookAccountId());
        user.setGoogleAccountId(registerRequest.getGoogleAccountId());

        User savedUser = userRepository.save(user);

        return new RegisterResponse(savedUser);
    }
}