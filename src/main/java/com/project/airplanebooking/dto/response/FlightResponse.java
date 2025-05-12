package com.project.airplanebooking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.project.airplanebooking.model.Flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightResponse {
    private Long id;
    private String flightNumber;
    private AirportResponse departureAirport;
    private AirportResponse arrivalAirport;
    private AirlineResponse airline;
    private AirplaneResponse airplane;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private BigDecimal basePrice;
    private BigDecimal currentPrice;
    private String status;
    private Boolean isFull;
    private Integer delayMinutes;
    private String flightType;
    private Integer availableSeats;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FlightResponse(Flight flight) {
        this.id = flight.getId();
        this.flightNumber = flight.getFlightNo();
        this.departureAirport = new AirportResponse(flight.getDepartureAirport());
        this.arrivalAirport = new AirportResponse(flight.getArrivalAirport());
        this.airline = new AirlineResponse(flight.getAirline());
        this.airplane = new AirplaneResponse(flight.getAirplane());
        this.departureTime = flight.getDepartureTime();
        this.arrivalTime = flight.getArrivalTime();
        this.basePrice = BigDecimal.valueOf(flight.getBaseFare());
        this.currentPrice = flight.getCurrentPrice() != null ? BigDecimal.valueOf(flight.getCurrentPrice()) : null;
        this.status = flight.getStatus();
        this.isFull = flight.getIsFull();
        this.delayMinutes = flight.getDelayMinutes();
        this.flightType = flight.getFlightType();
        this.availableSeats = flight.getAvailableSeats();
        this.createdAt = flight.getCreatedAt();
        this.updatedAt = flight.getUpdatedAt();
    }
}
