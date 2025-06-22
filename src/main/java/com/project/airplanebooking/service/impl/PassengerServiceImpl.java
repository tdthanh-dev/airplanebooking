package com.project.airplanebooking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.airplanebooking.dto.request.PassengerDTO;
import com.project.airplanebooking.exception.EntityNotFoundException;
import com.project.airplanebooking.exception.ResourceNotFoundException;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Passenger;
import com.project.airplanebooking.repository.BookingRepository;
import com.project.airplanebooking.repository.PassengerRepository;
import com.project.airplanebooking.service.PassengerService;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository, BookingRepository bookingRepository) {
        this.passengerRepository = passengerRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Passenger createPassenger(PassengerDTO passengerDTO, Booking booking) {
        Passenger passenger = new Passenger();
        passenger.setFirstName(passengerDTO.getFirstName());
        passenger.setLastName(passengerDTO.getLastName());
        passenger.setPersonalId(passengerDTO.getPersonalId());
        passenger.setDateOfBirth(passengerDTO.getBirthDate());
        passenger.setGender(passengerDTO.getGender());
        passenger.getBookings().add(booking);
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

        if (passengerDTO.getPersonalId() != null) {
            passenger.setPersonalId(passengerDTO.getPersonalId());
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
                .orElseThrow(() -> new EntityNotFoundException(Passenger.class, id));
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public List<Passenger> getPassengersByBookingId(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        return passengerRepository.findByBooking(booking);
    }
}