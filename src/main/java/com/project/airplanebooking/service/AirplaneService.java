package com.project.airplanebooking.service;

import com.project.airplanebooking.dto.request.AirplaneDTO;
import com.project.airplanebooking.model.Airplane;
import com.project.airplanebooking.model.Airline;

import java.util.List;

public interface AirplaneService {
    Airplane createAirplane(AirplaneDTO airplaneDTO);

    Airplane updateAirplane(Long id, AirplaneDTO airplaneDTO);

    void deleteAirplane(Long id);

    Airplane getAirplaneById(Long id);

    Airplane getAirplaneByRegistrationNumber(String registrationNumber);

    List<Airplane> getAirplanesByAirline(Airline airline);

    List<Airplane> getAirplanesByModel(String model);

    List<Airplane> getAllAirplanes();
}