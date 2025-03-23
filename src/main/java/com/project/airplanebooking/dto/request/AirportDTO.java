package com.project.airplanebooking.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirportDTO {

    @NotBlank(message = "Name is required")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "City is required")
    @JsonProperty("city")
    private String city;

    @NotBlank(message = "State is required")
    @JsonProperty("state")
    private String state;

    @NotBlank(message = "Country is required")
    @JsonProperty("country")
    private String country;

    @NotBlank(message = "IATA code is required")
    @JsonProperty("iata_code")
    private String iataCode;

    @NotBlank(message = "ICAO code is required")
    @JsonProperty("icao_code")
    private String icaoCode;
}