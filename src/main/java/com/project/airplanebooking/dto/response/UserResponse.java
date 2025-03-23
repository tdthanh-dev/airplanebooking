package com.project.airplanebooking.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String token;
    private String refreshToken;
    private String role;
    private String status;
    private Boolean isActive;
    private LocalDateTime lastLogin;
    private Integer facebookAccountId;
    private Integer googleAccountId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class RegisterResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private String role;

}
