package com.project.airplanebooking.service;

import com.project.airplanebooking.dto.request.AirlineDTO;
import com.project.airplanebooking.model.Airline;

import java.util.List;

public interface AirlineService {
    Airline createAirline(AirlineDTO airlineDTO);

    Airline updateAirline(Long id, AirlineDTO airlineDTO);

    void deleteAirline(Long id);

    Airline getAirlineById(Long id);

    Airline getAirlineByCode(String code);

    List<Airline> getAllAirlines();

    boolean existsByCode(String code);
}