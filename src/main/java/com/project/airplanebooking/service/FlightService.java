package com.project.airplanebooking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.project.airplanebooking.dto.request.FlightDTO;
import com.project.airplanebooking.model.Flight;

public interface FlightService {
    Flight createFlight(FlightDTO flightDTO);

    List<Flight> getAllFlights();

    Flight getFlightById(Long id);

    Flight getFlightByFlightNumber(String flightNumber);

    List<Flight> searchFlights(String departureAirportCode, String arrivalAirportCode, LocalDate departureDate);

    List<Flight> getFlightsByAirline(String airlineCode);

    Flight updateFlight(Long id, FlightDTO flightDTO);

    void deleteFlight(Long id);

    void updateFlightStatus(Long id, String status);

    List<Flight> searchFlightsOneWay(String departureAirport, String arrivalAirport, LocalDate departureDate,
            Integer totalPassengers);

    List<Flight> searchFlightsRoundTrip(String departureAirport, String arrivalAirport, LocalDate departureDate,
            LocalDate returnDate, Integer totalPassengers);
}