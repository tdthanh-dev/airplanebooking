package com.project.airplanebooking.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "Username is required")
    @JsonProperty("username")
    private String username;
    @NotBlank(message = "Password is required")
    @JsonProperty("password")
    private String password;
}
