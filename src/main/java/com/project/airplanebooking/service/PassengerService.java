package com.project.airplanebooking.service;

import com.project.airplanebooking.dto.request.PassengerDTO;
import com.project.airplanebooking.model.Passenger;
import com.project.airplanebooking.model.Booking;

import java.util.List;

public interface PassengerService {
    Passenger createPassenger(PassengerDTO passengerDTO);

    Passenger updatePassenger(Long id, PassengerDTO passengerDTO);

    void deletePassenger(Long id);

    Passenger getPassengerById(Long id);

    List<Passenger> getPassengersByBooking(Booking booking);

    List<Passenger> getPassengersByLastName(String lastName);

    List<Passenger> getPassengersByPassportNumber(String passportNumber);

    List<Passenger> getAllPassengers();
}