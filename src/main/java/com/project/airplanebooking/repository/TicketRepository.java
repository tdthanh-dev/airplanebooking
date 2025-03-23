package com.project.airplanebooking.repository;

import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Passenger;
import com.project.airplanebooking.model.Seat;
import com.project.airplanebooking.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByTicketNumber(String ticketNumber);

    List<Ticket> findByBooking(Booking booking);

    List<Ticket> findByPassenger(Passenger passenger);

    List<Ticket> findByFlight(Flight flight);

    List<Ticket> findBySeat(Seat seat);

    Optional<Ticket> findByFlightAndSeat(Flight flight, Seat seat);
}
