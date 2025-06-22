package com.project.airplanebooking.service;

import java.util.List;

import com.project.airplanebooking.dto.request.PassengerDTO;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Passenger;

public interface PassengerService {
    Passenger createPassenger(PassengerDTO passengerDTO, Booking booking);

    Passenger updatePassenger(Long id, PassengerDTO passengerDTO);

    void deletePassenger(Long id);

    Passenger getPassengerById(Long id);

    List<Passenger> getAllPassengers();

    /**
     * Lấy danh sách hành khách theo booking
     * 
     * @param bookingId ID của booking cần tìm
     * @return Danh sách hành khách thuộc booking
     */
    List<Passenger> getPassengersByBookingId(Long bookingId);
}