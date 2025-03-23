package com.project.airplanebooking.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    @NotNull(message = "User ID is required")
    @JsonProperty("user_id")
    private Long userId;

    @NotNull(message = "Flight ID is required")
    @JsonProperty("flight_id")
    private Long flightId;

    @NotNull(message = "Passengers are required")
    @JsonProperty("passengers")
    private List<PassengerDTO> passengers;

    @NotNull(message = "Total price is required")
    @Positive(message = "Total price must be positive")
    @JsonProperty("total_price")
    private Double totalPrice;

    @JsonProperty("contact_email")
    private String contactEmail;

    @JsonProperty("contact_phone")
    private String contactPhone;

    @JsonProperty("special_requests")
    private String specialRequests;
}