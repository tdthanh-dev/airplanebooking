package com.project.airplanebooking.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {
    @NotBlank(message = "Full name is required")
    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("phone_number")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    private String phoneNumber;

    @Email(message = "Invalid email format")
    @JsonProperty("email")
    private String email;

    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @NotBlank(message = "Nationality is required")
    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("passport_number")
    private String passportNumber;

    @JsonProperty("booking_id")
    private Long bookingId;
}