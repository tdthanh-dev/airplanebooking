package com.project.airplanebooking.service.impl;

import com.project.airplanebooking.dto.request.BookingDTO;
import com.project.airplanebooking.dto.request.PassengerDTO;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Passenger;
import com.project.airplanebooking.model.User;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.repository.BookingRepository;
import com.project.airplanebooking.repository.UserRepository;
import com.project.airplanebooking.repository.FlightRepository;
import com.project.airplanebooking.service.BookingService;
import com.project.airplanebooking.service.PassengerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final PassengerService passengerService;

    public BookingServiceImpl(BookingRepository bookingRepository,
            UserRepository userRepository,
            FlightRepository flightRepository,
            PassengerService passengerService) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.passengerService = passengerService;
    }

    @Override
    @Transactional
    public Booking createBooking(BookingDTO bookingDTO) {
        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + bookingDTO.getUserId()));

        Flight flight = flightRepository.findById(bookingDTO.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + bookingDTO.getFlightId()));

        Booking booking = new Booking();
        booking.setBookingReference(generateBookingNumber());
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingDate(LocalDateTime.now());
        booking.setTotalPrice(bookingDTO.getTotalPrice());
        booking.setStatus("PENDING");
        booking.setPassengerCount(bookingDTO.getPassengers().size());
        booking.setTripType("ONE_WAY"); // Default to ONE_WAY, can be updated based on DTO

        // Save booking first to get the ID
        booking = bookingRepository.save(booking);

        // Process passengers
        List<Passenger> passengers = new ArrayList<>();
        for (PassengerDTO passengerDTO : bookingDTO.getPassengers()) {
            passengerDTO.setBookingId(booking.getId());
            Passenger passenger = passengerService.createPassenger(passengerDTO);
            passengers.add(passenger);
        }

        booking.setPassengers(passengers);
        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public Booking updateBooking(Long id, BookingDTO bookingDTO) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        if (bookingDTO.getFlightId() != null) {
            Flight flight = flightRepository.findById(bookingDTO.getFlightId())
                    .orElseThrow(() -> new RuntimeException("Flight not found with id: " + bookingDTO.getFlightId()));
            booking.setFlight(flight);
        }

        if (bookingDTO.getTotalPrice() != null) {
            booking.setTotalPrice(bookingDTO.getTotalPrice());
        }

        // Update passenger count if passengers list is provided
        if (bookingDTO.getPassengers() != null) {
            booking.setPassengerCount(bookingDTO.getPassengers().size());
        }

        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        bookingRepository.delete(booking);
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    @Override
    public Booking getBookingByBookingNumber(String bookingNumber) {
        return bookingRepository.findByBookingReference(bookingNumber)
                .orElseThrow(() -> new RuntimeException("Booking not found with booking number: " + bookingNumber));
    }

    @Override
    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    @Override
    public List<Booking> getBookingsByFlight(Flight flight) {
        return bookingRepository.findByFlight(flight);
    }

    @Override
    public List<Booking> getBookingsByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public void updateBookingStatus(Long id, String status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        booking.setStatus(status);
        bookingRepository.save(booking);
    }

    private String generateBookingNumber() {
        // Generate a random booking number with prefix "BK" followed by 8 alphanumeric
        // characters
        return "BK" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}