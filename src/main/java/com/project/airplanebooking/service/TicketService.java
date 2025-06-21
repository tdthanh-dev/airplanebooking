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

    // Xuất vé cho booking sau khi được xác nhận
    Ticket generateTicketForBooking(Long bookingId, Long passengerId, Long flightId, Long seatFlightId);

    // Xuất tất cả vé cho một booking
    List<Ticket> generateAllTicketsForBooking(Long bookingId);

    // Xuất vé cho booking theo từng chuyến bay
    List<Ticket> generateTicketsForBookingAndFlight(Long bookingId, Long flightId);

    // Tìm vé theo booking reference
    List<Ticket> getTicketsByBookingReference(String bookingReference);

    // Tìm vé theo passenger
    List<Ticket> getTicketsByPassenger(Long passengerId);

    // Tạo vé hoàn toàn tự động (không cần DTO chi tiết)
    Ticket createTicketAutomatically(Long bookingId, Long passengerId, Long flightId, Long seatFlightId);
}