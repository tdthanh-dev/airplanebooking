package com.project.airplanebooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Passenger;
import com.project.airplanebooking.model.SeatFlight;
import com.project.airplanebooking.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByBooking(Booking booking);

    List<Ticket> findByFlight(Flight flight);

    List<Ticket> findByPassenger(Passenger passenger);

    Optional<Ticket> findByTicketNumber(String ticketNumber);

    // Tìm vé theo booking và passenger và flight
    List<Ticket> findByBookingAndPassengerAndFlight(Booking booking, Passenger passenger, Flight flight);

    // Kiểm tra vé đã tồn tại cho combination này chưa
    boolean existsByBookingAndPassengerAndFlightAndSeatFlight(Booking booking, Passenger passenger, Flight flight,
            SeatFlight seatFlight);

    // Tìm vé theo booking reference
    List<Ticket> findByBookingBookingReference(String bookingReference);

    // Tìm vé theo status
    List<Ticket> findByStatus(String status);
}
