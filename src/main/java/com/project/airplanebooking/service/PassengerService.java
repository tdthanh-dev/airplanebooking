package com.project.airplanebooking.service;

import java.util.List;

import com.project.airplanebooking.dto.request.PassengerDTO;
import com.project.airplanebooking.model.Passenger;

public interface PassengerService {
    Passenger createPassenger(PassengerDTO passengerDTO);

    Passenger updatePassenger(Long id, PassengerDTO passengerDTO);

    void deletePassenger(Long id);

    Passenger getPassengerById(Long id);

    List<Passenger> getAllPassengers();

}