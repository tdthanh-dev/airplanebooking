package com.project.airplanebooking.service;

import com.project.airplanebooking.dto.request.AirportDTO;
import com.project.airplanebooking.model.Airport;

import java.util.List;

public interface AirportService {
    Airport createAirport(AirportDTO airportDTO);

    Airport updateAirport(Long id, AirportDTO airportDTO);

    void deleteAirport(Long id);

    Airport getAirportById(Long id);

    Airport getAirportByCode(String code);

    List<Airport> getAirportsByCity(String city);

    List<Airport> getAirportsByCountry(String country);

    List<Airport> getAllAirports();

    boolean existsByCode(String code);
}