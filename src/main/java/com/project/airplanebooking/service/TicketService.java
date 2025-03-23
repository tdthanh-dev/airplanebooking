package com.project.airplanebooking.service;

import com.project.airplanebooking.dto.request.TicketDTO;
import com.project.airplanebooking.model.Ticket;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Passenger;
import com.project.airplanebooking.model.Seat;

import java.util.List;

public interface TicketService {
    Ticket createTicket(TicketDTO ticketDTO);

    Ticket updateTicket(Long id, TicketDTO ticketDTO);

    void deleteTicket(Long id);

    Ticket getTicketById(Long id);

    Ticket getTicketByTicketNumber(String ticketNumber);

    List<Ticket> getTicketsByBooking(Booking booking);

    List<Ticket> getTicketsByPassenger(Passenger passenger);

    List<Ticket> getTicketsByFlight(Flight flight);

    List<Ticket> getTicketsBySeat(Seat seat);

    Ticket getTicketByFlightAndSeat(Flight flight, Seat seat);

    List<Ticket> getAllTickets();

    void updateTicketStatus(Long id, String status);
}