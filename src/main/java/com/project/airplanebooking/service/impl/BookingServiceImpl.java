package com.project.airplanebooking.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.airplanebooking.dto.request.BookingDTO;
import com.project.airplanebooking.dto.request.PassengerDTO;
import com.project.airplanebooking.exception.BadRequestException;
import com.project.airplanebooking.exception.ResourceNotFoundException;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.Flight;
import com.project.airplanebooking.model.Passenger;
import com.project.airplanebooking.model.SeatFlight;
import com.project.airplanebooking.model.User;
import com.project.airplanebooking.repository.BookingRepository;
import com.project.airplanebooking.repository.FlightRepository;
import com.project.airplanebooking.repository.PassengerRepository;
import com.project.airplanebooking.repository.SeatFlightRepository;
import com.project.airplanebooking.repository.UserRepository;
import com.project.airplanebooking.service.BookingService;
import com.project.airplanebooking.service.PassengerService;
import com.project.airplanebooking.service.SeatFlightService;
import com.project.airplanebooking.service.TicketService;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final PassengerRepository passengerRepository;
    private final SeatFlightService seatFlightService;
    private final SeatFlightRepository seatFlightRepository;
    private final TicketService ticketService;
    private final PassengerService passengerService;

    @Autowired
    public BookingServiceImpl(
            BookingRepository bookingRepository,
            FlightRepository flightRepository,
            UserRepository userRepository,
            PassengerRepository passengerRepository,
            SeatFlightService seatFlightService,
            PassengerService passengerService,
            SeatFlightRepository seatFlightRepository,
            TicketService ticketService) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
        this.passengerRepository = passengerRepository;
        this.seatFlightService = seatFlightService;
        this.seatFlightRepository = seatFlightRepository;
        this.ticketService = ticketService;
        this.passengerService = passengerService;
    }

    @Override
    @Transactional
    public Booking createBooking(BookingDTO bookingDTO) {
        return createBookingWithPassengers(bookingDTO);
    }

    @Override
    @Transactional
    public Booking createBooking(Long flightId, Long userId, List<Passenger> passengers, String tripType) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + flightId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBookingReference(generateBookingReference());
        booking.setBookingDate(LocalDateTime.now());
        booking.setTotalPrice(calculateTotalPrice(flight, passengers.size()));
        booking.setStatus("PENDING");
        booking.setPassengerCount(passengers.size());
        booking.setTripType(tripType);
        booking.setBookingSource("ONLINE");
        booking.setPromotionCode("");
        booking.setCancellationReason("");

        Booking savedBooking = bookingRepository.save(booking);

        for (Passenger passenger : passengers) {
            PassengerDTO passengerDTO = new PassengerDTO();
            passengerDTO.setFirstName(passenger.getFirstName());
            passengerDTO.setLastName(passenger.getLastName());
            passengerDTO.setBirthDate(passenger.getDateOfBirth());
            passengerDTO.setGender(passenger.getGender());
            passengerDTO.setPersonalId(passenger.getPersonalId());
            passengerService.createPassenger(passengerDTO, savedBooking);
        }

        return bookingRepository.save(savedBooking);
    }

    @Override
    @Transactional
    public Booking createBookingWithPassengers(BookingDTO bookingDTO) {
        // Kiểm tra user
        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + bookingDTO.getUserId()));

        // Kiểm tra null cho các trường quan trọng
        if (bookingDTO.getTripType() == null || bookingDTO.getFlightIds() == null ||
                bookingDTO.getSeatFlightIds() == null || bookingDTO.getPassengerCount() == null ||
                bookingDTO.getPassengers() == null) {
            throw new BadRequestException("Missing required booking information");
        }

        // Kiểm tra tripType và số lượng flights
        if ("ROUND_TRIP".equals(bookingDTO.getTripType())) {
            if (bookingDTO.getFlightIds().size() != 2) {
                throw new BadRequestException("Round trip booking must have exactly 2 flights (outbound and return)");
            }
            if (bookingDTO.getSeatFlightIds().size() < bookingDTO.getPassengerCount() * 2) {
                throw new BadRequestException(
                        "Round trip booking must have seats for both outbound and return flights");
            }
        } else if ("ONE_WAY".equals(bookingDTO.getTripType())) {
            if (bookingDTO.getFlightIds().size() != 1) {
                throw new BadRequestException("One way booking must have exactly 1 flight");
            }
            if (bookingDTO.getSeatFlightIds().size() < bookingDTO.getPassengerCount()) {
                throw new BadRequestException(
                        "One way booking must have at least " + bookingDTO.getPassengerCount() + " seats");
            }
        } else {
            throw new BadRequestException("Invalid trip type: " + bookingDTO.getTripType());
        }

        // Kiểm tra flights có tồn tại
        List<Flight> flights = flightRepository.findAllById(bookingDTO.getFlightIds());
        if (flights.size() != bookingDTO.getFlightIds().size()) {
            throw new ResourceNotFoundException("One or more flights not found");
        }

        // Kiểm tra seats có tồn tại và available
        List<SeatFlight> seatFlights = seatFlightRepository.findAllById(bookingDTO.getSeatFlightIds());
        if (seatFlights.size() != bookingDTO.getSeatFlightIds().size()) {
            throw new ResourceNotFoundException("One or more seats not found");
        }

        for (SeatFlight seatFlight : seatFlights) {
            if (!"AVAILABLE".equals(seatFlight.getStatus())) {
                throw new BadRequestException("Seat " + seatFlight.getId() + " is not available");
            }
        }

        // Tính toán và kiểm tra giá vé
        double calculatedPrice = 0;
        for (Flight flight : flights) {
            calculatedPrice += flight.getCurrentPrice() * bookingDTO.getPassengerCount();
        }

        // Tạo booking mới
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBookingReference(generateBookingReference());
        booking.setBookingDate(LocalDateTime.now());
        booking.setTotalPrice(calculatedPrice); // Dùng giá tính toán từ server
        booking.setStatus("PENDING");
        booking.setPassengerCount(bookingDTO.getPassengerCount());
        booking.setTripType(bookingDTO.getTripType());
        booking.setBookingSource(bookingDTO.getBookingSource() != null ? bookingDTO.getBookingSource() : "ONLINE");
        booking.setPromotionCode(bookingDTO.getPromotionCode() != null ? bookingDTO.getPromotionCode() : "");
        booking.setCancellationReason("");
        booking.setFlights(flights);

        // Lưu booking trước để có ID
        Booking savedBooking = bookingRepository.save(booking);

        // Tạo và lưu passengers
        for (PassengerDTO passengerDTO : bookingDTO.getPassengers()) {
            // Kiểm tra thông tin passenger
            if (passengerDTO.getFirstName() == null || passengerDTO.getLastName() == null ||
                    passengerDTO.getBirthDate() == null || passengerDTO.getGender() == null) {
                throw new BadRequestException("Missing required passenger information");
            }

            // Sử dụng service để tạo passenger
            passengerService.createPassenger(passengerDTO, savedBooking);
        }

        // Cập nhật trạng thái ghế
        for (SeatFlight seatFlight : seatFlights) {
            seatFlight.setStatus("HOLD");
            seatFlight.setBooking(savedBooking);
            seatFlight.setDateHold(LocalDateTime.now());
            seatFlightRepository.save(seatFlight);
        }

        savedBooking.setSeatFlights(seatFlights);

        return bookingRepository.save(savedBooking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
    }

    @Override
    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    @Override
    public List<Booking> getBookingsByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }

    @Override
    public Booking getBookingByBookingReference(String bookingReference) {
        return bookingRepository.findByBookingReference(bookingReference)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Booking not found with reference: " + bookingReference));
    }

    @Override
    @Transactional
    public Booking updateBooking(Long id, BookingDTO bookingDTO) {
        Booking booking = getBookingById(id);

        if (bookingDTO.getStatus() != null) {
            booking.setStatus(bookingDTO.getStatus());
        }

        if (bookingDTO.getPassengerCount() != null) {
            booking.setPassengerCount(bookingDTO.getPassengerCount());
        }

        if (bookingDTO.getTripType() != null) {
            booking.setTripType(bookingDTO.getTripType());
        }

        if (bookingDTO.getBookingSource() != null) {
            booking.setBookingSource(bookingDTO.getBookingSource());
        }

        if (bookingDTO.getPromotionCode() != null) {
            booking.setPromotionCode(bookingDTO.getPromotionCode());
        }

        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public Booking updateBookingStatus(Long id, String status) {
        Booking booking = getBookingById(id);
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public void updateBookingSeats(Long id, Integer seats) {
        Booking booking = getBookingById(id);
        booking.setPassengerCount(seats);
        bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public void cancelBooking(Long id) {
        Booking booking = getBookingById(id);
        booking.setStatus("CANCELLED");

        for (SeatFlight seatFlight : booking.getSeatFlights()) {
            if ("HOLD".equals(seatFlight.getStatus()) || "BOOKED".equals(seatFlight.getStatus())) {
                seatFlight.setStatus("AVAILABLE");
                seatFlight.setBooking(null);
                seatFlight.setDateHold(null);
                seatFlightRepository.save(seatFlight);
            }
        }

        bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public boolean cancelBooking(Long id, String reason) {
        try {
            Booking booking = getBookingById(id);
            booking.setStatus("CANCELLED");
            booking.setCancellationReason(reason);

            for (SeatFlight seatFlight : booking.getSeatFlights()) {
                if ("HOLD".equals(seatFlight.getStatus()) || "BOOKED".equals(seatFlight.getStatus())) {
                    seatFlight.setStatus("AVAILABLE");
                    seatFlight.setBooking(null);
                    seatFlight.setDateHold(null);
                    seatFlightRepository.save(seatFlight);
                }
            }

            bookingRepository.save(booking);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public Booking confirmBooking(Long id) {
        Booking booking = getBookingById(id);

        if (!"PENDING".equals(booking.getStatus())) {
            throw new BadRequestException("Booking can only be confirmed if it's in PENDING status");
        }

        booking.setStatus("CONFIRMED");

        for (SeatFlight seatFlight : booking.getSeatFlights()) {
            if ("HOLD".equals(seatFlight.getStatus())) {
                seatFlight.setStatus("BOOKED");
                seatFlightRepository.save(seatFlight);
            }
        }

        Booking savedBooking = bookingRepository.save(booking);

        try {
            ticketService.generateAllTicketsForBooking(savedBooking.getId());
        } catch (Exception e) {
            System.err.println("Lỗi tạo vé tự động cho booking " + savedBooking.getId() + ": " + e.getMessage());
        }

        return savedBooking;
    }

    private String generateBookingReference() {
        return "B" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private Double calculateTotalPrice(Flight flight, int passengerCount) {
        return flight.getCurrentPrice() * passengerCount;
    }
}