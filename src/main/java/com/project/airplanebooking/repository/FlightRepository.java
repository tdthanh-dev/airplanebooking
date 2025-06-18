package com.project.airplanebooking.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.airplanebooking.model.Airline;
import com.project.airplanebooking.model.Airport;
import com.project.airplanebooking.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
        Optional<Flight> findByFlightNo(String flightNo);

        List<Flight> findByDepartureAirport(Airport departureAirport);

        List<Flight> findByArrivalAirport(Airport arrivalAirport);

        List<Flight> findByAirline(Airline airline);

        List<Flight> findByDepartureAirportAndArrivalAirport(Airport departureAirport, Airport arrivalAirport);

        List<Flight> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);

        List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureTimeGreaterThanEqual(
                        Airport departureAirport, Airport arrivalAirport, LocalDateTime departureTime);

        List<Flight> findByDepartureAirportIataCodeAndArrivalAirportIataCodeAndDepartureTimeBetween(
                        String departureAirportCode, String arrivalAirportCode, LocalDateTime startTime,
                        LocalDateTime endTime);

}
