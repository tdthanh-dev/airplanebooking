package com.project.airplanebooking.repository;

import com.project.airplanebooking.model.Airport;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findByFlightNo(String flightNo);

    List<Flight> findByDepartureAirport(Airport departureAirport);

    List<Flight> findByArrivalAirport(Airport arrivalAirport);

    List<Flight> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Flight> findByAirline(Airline airline);

    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureTimeBetween(
            Airport departureAirport, Airport arrivalAirport,
            LocalDateTime start, LocalDateTime end);
}
