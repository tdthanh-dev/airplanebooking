package com.project.airplanebooking.dto.response;

import java.time.LocalDateTime;

import com.project.airplanebooking.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
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

    public RegisterResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.role = user.getRole();
        this.loyaltyPoints = user.getLoyaltyPoints();
        this.lastSearchedRoute = user.getLastSearchedRoute();
        this.preferredAirportId = user.getPreferredAirportId() != null ? Long.valueOf(user.getPreferredAirportId())
                : null;
        this.preferredSeatClass = user.getPreferredSeatClass();
        this.preferredAirlineId = user.getPreferredAirlineId() != null ? Long.valueOf(user.getPreferredAirlineId())
                : null;
        this.status = user.getStatus();
        this.isActive = user.getIsActive();
        this.lastLogin = user.getLastLogin();
        this.facebookAccountId = user.getFacebookAccountId();
        this.googleAccountId = user.getGoogleAccountId();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
