package com.project.airplanebooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Passenger;
import com.project.airplanebooking.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByBooking(Booking booking);

    List<Ticket> findByFlight(Flight flight);

    List<Ticket> findByPassenger(Passenger passenger);

    Optional<Ticket> findByTicketNumber(String ticketNumber);
}
