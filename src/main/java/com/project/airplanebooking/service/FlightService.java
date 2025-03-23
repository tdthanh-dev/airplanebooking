package com.project.airplanebooking.service;

import com.project.airplanebooking.dto.request.FlightDTO;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Airport;
import com.project.airplanebooking.model.Airline;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    Flight createFlight(FlightDTO flightDTO);

    Flight updateFlight(Long id, FlightDTO flightDTO);

    void deleteFlight(Long id);

    Flight getFlightById(Long id);

    Flight getFlightByFlightNumber(String flightNumber);

    List<Flight> getFlightsByDepartureAirport(Airport departureAirport);

    List<Flight> getFlightsByArrivalAirport(Airport arrivalAirport);

    List<Flight> getFlightsByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Flight> getFlightsByAirline(Airline airline);

    List<Flight> searchFlights(Airport departureAirport, Airport arrivalAirport,
            LocalDateTime departureDate);

    List<Flight> getAllFlights();

    void updateFlightStatus(Long id, String status);
}