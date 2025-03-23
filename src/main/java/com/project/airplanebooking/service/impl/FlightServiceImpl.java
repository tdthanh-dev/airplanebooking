package com.project.airplanebooking.service.impl;

import com.project.airplanebooking.dto.request.FlightDTO;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Airport;
import com.project.airplanebooking.model.Airline;
import com.project.airplanebooking.model.Airplane;
import com.project.airplanebooking.repository.FlightRepository;
import com.project.airplanebooking.repository.AirportRepository;
import com.project.airplanebooking.repository.AirlineRepository;
import com.project.airplanebooking.repository.AirplaneRepository;
import com.project.airplanebooking.service.FlightService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final AirlineRepository airlineRepository;
    private final AirplaneRepository airplaneRepository;

    public FlightServiceImpl(FlightRepository flightRepository,
            AirportRepository airportRepository,
            AirlineRepository airlineRepository,
            AirplaneRepository airplaneRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.airlineRepository = airlineRepository;
        this.airplaneRepository = airplaneRepository;
    }

    @Override
    public Flight createFlight(FlightDTO flightDTO) {
        Airport departureAirport = airportRepository.findById(flightDTO.getDepartureAirportId())
                .orElseThrow(() -> new RuntimeException(
                        "Departure airport not found with id: " + flightDTO.getDepartureAirportId()));

        Airport arrivalAirport = airportRepository.findById(flightDTO.getArrivalAirportId())
                .orElseThrow(() -> new RuntimeException(
                        "Arrival airport not found with id: " + flightDTO.getArrivalAirportId()));

        Airline airline = airlineRepository.findById(flightDTO.getAirlineId())
                .orElseThrow(() -> new RuntimeException("Airline not found with id: " + flightDTO.getAirlineId()));

        Airplane airplane = airplaneRepository.findById(flightDTO.getAirplaneId())
                .orElseThrow(() -> new RuntimeException("Airplane not found with id: " + flightDTO.getAirplaneId()));

        Flight flight = new Flight();
        flight.setFlightNo(flightDTO.getFlightNo());
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight.setAirline(airline);
        flight.setAirplane(airplane);
        flight.setBaseFare(flightDTO.getBasePrice());
        flight.setStatus(flightDTO.getStatus());

        // Calculate duration in minutes
        long durationMinutes = java.time.Duration.between(
                flightDTO.getDepartureTime(), flightDTO.getArrivalTime()).toMinutes();
        flight.setDurationMinutes((int) durationMinutes);

        return flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Long id, FlightDTO flightDTO) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));

        if (flightDTO.getDepartureAirportId() != null) {
            Airport departureAirport = airportRepository.findById(flightDTO.getDepartureAirportId())
                    .orElseThrow(() -> new RuntimeException(
                            "Departure airport not found with id: " + flightDTO.getDepartureAirportId()));
            flight.setDepartureAirport(departureAirport);
        }

        if (flightDTO.getArrivalAirportId() != null) {
            Airport arrivalAirport = airportRepository.findById(flightDTO.getArrivalAirportId())
                    .orElseThrow(() -> new RuntimeException(
                            "Arrival airport not found with id: " + flightDTO.getArrivalAirportId()));
            flight.setArrivalAirport(arrivalAirport);
        }

        if (flightDTO.getAirlineId() != null) {
            Airline airline = airlineRepository.findById(flightDTO.getAirlineId())
                    .orElseThrow(() -> new RuntimeException("Airline not found with id: " + flightDTO.getAirlineId()));
            flight.setAirline(airline);
        }

        if (flightDTO.getAirplaneId() != null) {
            Airplane airplane = airplaneRepository.findById(flightDTO.getAirplaneId())
                    .orElseThrow(
                            () -> new RuntimeException("Airplane not found with id: " + flightDTO.getAirplaneId()));
            flight.setAirplane(airplane);
        }

        flight.setFlightNo(flightDTO.getFlightNo());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight.setBaseFare(flightDTO.getBasePrice());
        flight.setStatus(flightDTO.getStatus());

        // Recalculate duration in minutes if times have changed
        long durationMinutes = java.time.Duration.between(
                flightDTO.getDepartureTime(), flightDTO.getArrivalTime()).toMinutes();
        flight.setDurationMinutes((int) durationMinutes);

        return flightRepository.save(flight);
    }

    @Override
    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));

        flightRepository.delete(flight);
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));
    }

    @Override
    public Flight getFlightByFlightNumber(String flightNumber) {
        return flightRepository.findByFlightNo(flightNumber)
                .orElseThrow(() -> new RuntimeException("Flight not found with flight number: " + flightNumber));
    }

    @Override
    public List<Flight> getFlightsByDepartureAirport(Airport departureAirport) {
        return flightRepository.findByDepartureAirport(departureAirport);
    }

    @Override
    public List<Flight> getFlightsByArrivalAirport(Airport arrivalAirport) {
        return flightRepository.findByArrivalAirport(arrivalAirport);
    }

    @Override
    public List<Flight> getFlightsByDepartureTimeBetween(LocalDateTime start, LocalDateTime end) {
        return flightRepository.findByDepartureTimeBetween(start, end);
    }

    @Override
    public List<Flight> getFlightsByAirline(Airline airline) {
        return flightRepository.findByAirline(airline);
    }

    @Override
    public List<Flight> searchFlights(Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDate) {
        // Assuming departureDate is start of day, we need end of day for search
        LocalDateTime endOfDay = departureDate.toLocalDate().atTime(23, 59, 59);

        return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureTimeBetween(
                departureAirport, arrivalAirport, departureDate, endOfDay);
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public void updateFlightStatus(Long id, String status) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));

        flight.setStatus(status);
        flightRepository.save(flight);
    }
}