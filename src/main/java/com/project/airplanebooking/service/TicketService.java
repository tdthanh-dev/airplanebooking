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

    /**
     * Tạo vé cho tất cả hành khách và chuyến bay của một booking
     * Transaction mới và độc lập với booking transaction
     * 
     * @param bookingId ID của booking cần tạo vé
     * @return Danh sách các vé đã tạo
     */
    List<Ticket> generateAllTicketsForBooking(Long bookingId);
}