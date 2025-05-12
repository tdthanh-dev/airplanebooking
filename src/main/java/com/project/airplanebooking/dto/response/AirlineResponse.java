package com.project.airplanebooking.dto.response;

import java.time.LocalDateTime;

import com.project.airplanebooking.model.Airline;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirlineResponse {
    private Long id;
    private String name;
    private String iataCode;
    private String icaoCode;
    private String callSign;
    private String country;
    private String website;
    private String hotline;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AirlineResponse(Airline airline) {
        this.id = airline.getId();
        this.name = airline.getName();
        this.iataCode = airline.getIataCode();
        this.icaoCode = airline.getIcaoCode();
        this.callSign = airline.getCallSign();
        this.country = airline.getCountry();
        this.website = airline.getWebsite();
        this.hotline = airline.getHotline();
        this.createdAt = airline.getCreatedAt();
        this.updatedAt = airline.getUpdatedAt();
    }
}