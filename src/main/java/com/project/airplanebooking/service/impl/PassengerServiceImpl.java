package com.project.airplanebooking.service.impl;

import com.project.airplanebooking.dto.request.PassengerDTO;
import com.project.airplanebooking.model.Passenger;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.repository.PassengerRepository;
import com.project.airplanebooking.repository.BookingRepository;
import com.project.airplanebooking.service.PassengerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final BookingRepository bookingRepository;

    public PassengerServiceImpl(PassengerRepository passengerRepository, BookingRepository bookingRepository) {
        this.passengerRepository = passengerRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Passenger createPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger();
        passenger.setFullName(passengerDTO.getFullName());
        passenger.setPhoneNumber(passengerDTO.getPhoneNumber());
        passenger.setEmail(passengerDTO.getEmail());
        passenger.setGender(passengerDTO.getGender());
        passenger.setPassportNumber(passengerDTO.getPassportNumber());
        passenger.setNationality(passengerDTO.getNationality());
        passenger.setBirthDate(passengerDTO.getBirthDate());

        if (passengerDTO.getBookingId() != null) {
            Booking booking = bookingRepository.findById(passengerDTO.getBookingId())
                    .orElseThrow(
                            () -> new RuntimeException("Booking not found with id: " + passengerDTO.getBookingId()));
            passenger.setBooking(booking);
        }

        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger updatePassenger(Long id, PassengerDTO passengerDTO) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + id));

        passenger.setFullName(passengerDTO.getFullName());
        passenger.setPhoneNumber(passengerDTO.getPhoneNumber());
        passenger.setEmail(passengerDTO.getEmail());
        passenger.setGender(passengerDTO.getGender());
        passenger.setPassportNumber(passengerDTO.getPassportNumber());
        passenger.setNationality(passengerDTO.getNationality());
        passenger.setBirthDate(passengerDTO.getBirthDate());

        if (passengerDTO.getBookingId() != null) {
            Booking booking = bookingRepository.findById(passengerDTO.getBookingId())
                    .orElseThrow(
                            () -> new RuntimeException("Booking not found with id: " + passengerDTO.getBookingId()));
            passenger.setBooking(booking);
        } else {
            passenger.setBooking(null);
        }

        return passengerRepository.save(passenger);
    }

    @Override
    public void deletePassenger(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + id));

        passengerRepository.delete(passenger);
    }

    @Override
    public Passenger getPassengerById(Long id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + id));
    }

    @Override
    public List<Passenger> getPassengersByBooking(Booking booking) {
        return passengerRepository.findByBooking(booking);
    }

    @Override
    public List<Passenger> getPassengersByLastName(String lastName) {
        return passengerRepository.findByFullNameContaining(lastName);
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