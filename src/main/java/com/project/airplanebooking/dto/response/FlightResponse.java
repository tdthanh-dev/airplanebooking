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

        if (flight.getDepartureAirport() != null) {
            this.departureAirport = new AirportResponse();
            this.departureAirport.setId(flight.getDepartureAirport().getId());
            this.departureAirport.setName(flight.getDepartureAirport().getName());
            this.departureAirport.setCity(flight.getDepartureAirport().getCity());
            this.departureAirport.setState(flight.getDepartureAirport().getState());
            this.departureAirport.setCountry(flight.getDepartureAirport().getCountry());
            this.departureAirport.setIataCode(flight.getDepartureAirport().getIataCode());
            this.departureAirport.setIcaoCode(flight.getDepartureAirport().getIcaoCode());
        }

        if (flight.getArrivalAirport() != null) {
            this.arrivalAirport = new AirportResponse();
            this.arrivalAirport.setId(flight.getArrivalAirport().getId());
            this.arrivalAirport.setName(flight.getArrivalAirport().getName());
            this.arrivalAirport.setCity(flight.getArrivalAirport().getCity());
            this.arrivalAirport.setState(flight.getArrivalAirport().getState());
            this.arrivalAirport.setCountry(flight.getArrivalAirport().getCountry());
            this.arrivalAirport.setIataCode(flight.getArrivalAirport().getIataCode());
            this.arrivalAirport.setIcaoCode(flight.getArrivalAirport().getIcaoCode());
        }

        if (flight.getAirline() != null) {
            this.airline = new AirlineResponse();
            this.airline.setId(flight.getAirline().getId());
            this.airline.setName(flight.getAirline().getName());
            this.airline.setIataCode(flight.getAirline().getIataCode());
            this.airline.setIcaoCode(flight.getAirline().getIcaoCode());
            this.airline.setCallSign(flight.getAirline().getCallSign());
            this.airline.setCountry(flight.getAirline().getCountry());
            this.airline.setWebsite(flight.getAirline().getWebsite());
            this.airline.setHotline(flight.getAirline().getHotline());
        }

        if (flight.getAirplane() != null) {
            this.airplane = new AirplaneResponse();
            this.airplane.setId(flight.getAirplane().getId());
            this.airplane.setModel(flight.getAirplane().getModel());
            this.airplane.setRegistrationNumber(flight.getAirplane().getRegistrationNumber());
            this.airplane.setSeatCapacity(flight.getAirplane().getSeatCapacity());
            // Không truy cập vào airline để tránh vòng lặp vô tận
            if (flight.getAirplane().getAirline() != null) {
                AirlineResponse airlineResponse = new AirlineResponse();
                airlineResponse.setId(flight.getAirplane().getAirline().getId());
                airlineResponse.setName(flight.getAirplane().getAirline().getName());
                this.airplane.setAirline(airlineResponse);
            }
        }

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
