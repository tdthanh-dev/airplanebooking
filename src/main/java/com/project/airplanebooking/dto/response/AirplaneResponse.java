package com.project.airplanebooking.dto.response;

import java.time.LocalDateTime;

import com.project.airplanebooking.model.Airplane;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirplaneResponse {
    private Long id;
    private String model;
    private AirlineResponse airline;
    private String registrationNumber;
    private Integer seatCapacity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AirplaneResponse(Airplane airplane) {
        this.id = airplane.getId();
        this.model = airplane.getModel();
        this.airline = new AirlineResponse(airplane.getAirline());
        this.registrationNumber = airplane.getRegistrationNumber();
        this.seatCapacity = airplane.getSeatCapacity();
        this.createdAt = airplane.getCreatedAt();
        this.updatedAt = airplane.getUpdatedAt();
    }
}