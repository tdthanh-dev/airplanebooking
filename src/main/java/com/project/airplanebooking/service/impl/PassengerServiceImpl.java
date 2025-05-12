package com.project.airplanebooking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.airplanebooking.dto.request.PassengerDTO;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Passenger;
import com.project.airplanebooking.repository.BookingRepository;
import com.project.airplanebooking.repository.PassengerRepository;
import com.project.airplanebooking.service.PassengerService;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Passenger createPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger();
        passenger.setFirstName(passengerDTO.getFirstName());
        passenger.setLastName(passengerDTO.getLastName());
        passenger.setPersonalId(passengerDTO.getPersonalId());
        passenger.setDateOfBirth(passengerDTO.getBirthDate());
        passenger.setGender(passengerDTO.getGender());
        passenger.setNationality(passengerDTO.getNationality());
        passenger.setPassportNumber(passengerDTO.getPassportNumber());
        passenger.setEmail(passengerDTO.getEmail() != null ? passengerDTO.getEmail() : "");
        passenger.setPhone(passengerDTO.getPhone() != null ? passengerDTO.getPhone() : "");

        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger updatePassenger(Long id, PassengerDTO passengerDTO) {
        Passenger passenger = getPassengerById(id);

        if (passengerDTO.getFirstName() != null) {
            passenger.setFirstName(passengerDTO.getFirstName());
        }

        if (passengerDTO.getLastName() != null) {
            passenger.setLastName(passengerDTO.getLastName());
        }

        if (passengerDTO.getBirthDate() != null) {
            passenger.setDateOfBirth(passengerDTO.getBirthDate());
        }

        if (passengerDTO.getGender() != null) {
            passenger.setGender(passengerDTO.getGender());
        }

        if (passengerDTO.getNationality() != null) {
            passenger.setNationality(passengerDTO.getNationality());
        }

        if (passengerDTO.getPassportNumber() != null) {
            passenger.setPassportNumber(passengerDTO.getPassportNumber());
        }

        if (passengerDTO.getPersonalId() != null) {
            passenger.setPersonalId(passengerDTO.getPersonalId());
        }

        if (passengerDTO.getEmail() != null) {
            passenger.setEmail(passengerDTO.getEmail());
        }

        if (passengerDTO.getPhone() != null) {
            passenger.setPhone(passengerDTO.getPhone());
        }

        return passengerRepository.save(passenger);
    }

    @Override
    public void deletePassenger(Long id) {
        Passenger passenger = getPassengerById(id);
        passengerRepository.delete(passenger);
    }

    @Override
    public Passenger getPassengerById(Long id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
    }

    @Override
    public List<Passenger> getPassengersByBooking(Booking booking) {
        return passengerRepository.findByBooking(booking);
    }

    @Override
    public List<Passenger> getPassengersByBookingId(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return passengerRepository.findByBooking(booking);
    }

    @Override
    public List<Passenger> getPassengersByLastName(String lastName) {
        return passengerRepository.findByLastNameContaining(lastName);
    }

    @Override
    public List<Passenger> getPassengersByPassportNumber(String passportNumber) {
        return passengerRepository.findByPassportNumber(passportNumber);
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }
}