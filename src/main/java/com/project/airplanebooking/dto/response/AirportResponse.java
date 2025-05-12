package com.project.airplanebooking.dto.response;

import java.time.LocalDateTime;

import com.project.airplanebooking.model.Airport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirportResponse {
    private Long id;
    private String name;
    private String city;
    private String state;
    private String country;
    private String iataCode;
    private String icaoCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AirportResponse(Airport airport) {
        this.id = airport.getId();
        this.name = airport.getName();
        this.city = airport.getCity();
        this.state = airport.getState();
        this.country = airport.getCountry();
        this.iataCode = airport.getIataCode();
        this.icaoCode = airport.getIcaoCode();
        this.createdAt = airport.getCreatedAt();
        this.updatedAt = airport.getUpdatedAt();
    }
}