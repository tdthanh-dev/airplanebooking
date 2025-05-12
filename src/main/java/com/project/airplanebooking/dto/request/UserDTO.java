package com.project.airplanebooking.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be valid format")
    private String phone;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    private Integer loyaltyPoints;

    private String lastSearchedRoute;

    private Integer preferredAirportId;

    private String preferredSeatClass;

    private Integer preferredAirlineId;
}