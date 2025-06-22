package com.project.airplanebooking.dto.response;

import java.time.LocalDateTime;

import com.project.airplanebooking.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String token;
    private String refreshToken;
    private String role;
    private String status;
    private Boolean isActive;
    private LocalDateTime lastLogin;
    private Integer facebookAccountId;
    private Integer googleAccountId;
    private Integer loyaltyPoints;
    private String lastSearchedRoute;
    private Long preferredAirportId;
    private String preferredSeatClass;
    private Long preferredAirlineId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
        this.status = user.getStatus();
        this.isActive = user.getIsActive();
        this.lastLogin = user.getLastLogin();
        this.facebookAccountId = user.getFacebookAccountId();
        this.googleAccountId = user.getGoogleAccountId();
        this.loyaltyPoints = user.getLoyaltyPoints();
        this.lastSearchedRoute = user.getLastSearchedRoute();
        this.preferredAirportId = user.getPreferredAirportId() != null ? Long.valueOf(user.getPreferredAirportId())
                : null;
        this.preferredSeatClass = user.getPreferredSeatClass();
        this.preferredAirlineId = user.getPreferredAirlineId() != null ? Long.valueOf(user.getPreferredAirlineId())
                : null;
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
