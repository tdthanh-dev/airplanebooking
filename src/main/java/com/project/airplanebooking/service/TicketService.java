package com.project.airplanebooking.service;

import java.util.List;

import com.project.airplanebooking.dto.request.TicketDTO;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Passenger;
import com.project.airplanebooking.model.Ticket;

public interface TicketService {
    Ticket createTicket(TicketDTO ticketDTO);

    Ticket updateTicket(Long id, TicketDTO ticketDTO);

    void deleteTicket(Long id);

    Ticket getTicketById(Long id);

    List<Ticket> getAllTickets();

    List<Ticket> getTicketsByBooking(Booking booking);

    List<Ticket> getTicketsByFlight(Flight flight);

    List<Ticket> getTicketsByPassenger(Passenger passenger);

    void cancelTicket(Long id);

    Ticket getTicketByTicketNumber(String ticketNumber);

    Ticket createTicket(Booking booking, Passenger passenger, String seatNumber);

    List<Ticket> generateTicketsForBooking(Long bookingId);

    List<Ticket> getTicketsByBookingId(Long bookingId);

    Ticket updateTicketStatus(Long id, String status);

    boolean cancelTicketWithResult(Long id);
}