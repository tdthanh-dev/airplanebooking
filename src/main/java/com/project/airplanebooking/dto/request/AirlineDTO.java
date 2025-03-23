package com.project.airplanebooking.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirlineDTO {
    @NotBlank(message = "Name is required")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "IATA code is required")
    @JsonProperty("iata_code")
    private String iataCode;

    @NotBlank(message = "ICAO code is required")
    @JsonProperty("icao_code")
    private String icaoCode;

    @JsonProperty("call_sign")
    private String callSign;

    @NotBlank(message = "Country is required")
    @JsonProperty("country")
    private String country;

    @JsonProperty("website")
    private String website;

    @JsonProperty("hotline")
    private String hotline;
}
