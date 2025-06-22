package com.project.airplanebooking.service;

import java.util.List;

import com.project.airplanebooking.dto.request.TicketDTO;
import com.project.airplanebooking.model.Ticket;

public interface TicketService {
    Ticket createTicket(TicketDTO ticketDTO);

    Ticket updateTicket(Long id, TicketDTO ticketDTO);

    void deleteTicket(Long id);

    Ticket getTicketById(Long id);

    List<Ticket> getAllTickets();

    List<Ticket> generateTicket(Long bookingId);

    List<Ticket> generateAllTicketsForBooking(Long bookingId);
}