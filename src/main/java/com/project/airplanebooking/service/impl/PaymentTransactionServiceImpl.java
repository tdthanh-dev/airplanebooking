package com.project.airplanebooking.service.impl;

import com.project.airplanebooking.dto.request.PaymentTransactionDTO;
import com.project.airplanebooking.exception.EntityNotFoundException;
import com.project.airplanebooking.model.PaymentTransaction;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.PaymentMethod;
import com.project.airplanebooking.repository.PaymentTransactionRepository;
import com.project.airplanebooking.repository.BookingRepository;
import com.project.airplanebooking.repository.PaymentMethodRepository;
import com.project.airplanebooking.service.PaymentTransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentTransactionServiceImpl implements PaymentTransactionService {

    private final PaymentTransactionRepository paymentTransactionRepository;
    private final BookingRepository bookingRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentTransactionServiceImpl(PaymentTransactionRepository paymentTransactionRepository,
            BookingRepository bookingRepository,
            PaymentMethodRepository paymentMethodRepository) {
        this.paymentTransactionRepository = paymentTransactionRepository;
        this.bookingRepository = bookingRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public PaymentTransaction createPaymentTransaction(PaymentTransactionDTO paymentTransactionDTO) {
        Booking booking = bookingRepository.findById(paymentTransactionDTO.getBookingId())
                .orElseThrow(() -> new EntityNotFoundException(Booking.class, paymentTransactionDTO.getBookingId()));

        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentTransactionDTO.getPaymentMethodId())
                .orElseThrow(() -> new EntityNotFoundException(PaymentMethod.class,
                        paymentTransactionDTO.getPaymentMethodId()));

        PaymentTransaction paymentTransaction = new PaymentTransaction();
        paymentTransaction.setBooking(booking);
        paymentTransaction.setPaymentMethod(paymentMethod);
        paymentTransaction.setAmount(paymentTransactionDTO.getAmount());
        paymentTransaction.setTransactionDate(LocalDateTime.now());
        paymentTransaction
                .setStatus(paymentTransactionDTO.getStatus() != null ? paymentTransactionDTO.getStatus() : "PENDING");

        return paymentTransactionRepository.save(paymentTransaction);
    }

    @Override
    public PaymentTransaction updatePaymentTransaction(Long id, PaymentTransactionDTO paymentTransactionDTO) {
        PaymentTransaction paymentTransaction = paymentTransactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PaymentTransaction.class, id));

        if (paymentTransactionDTO.getBookingId() != null) {
            Booking booking = bookingRepository.findById(paymentTransactionDTO.getBookingId())
                    .orElseThrow(
                            () -> new EntityNotFoundException(Booking.class, paymentTransactionDTO.getBookingId()));
            paymentTransaction.setBooking(booking);
        }

        if (paymentTransactionDTO.getPaymentMethodId() != null) {
            PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentTransactionDTO.getPaymentMethodId())
                    .orElseThrow(() -> new EntityNotFoundException(PaymentMethod.class,
                            paymentTransactionDTO.getPaymentMethodId()));
            paymentTransaction.setPaymentMethod(paymentMethod);
        }

        if (paymentTransactionDTO.getAmount() != null) {
            paymentTransaction.setAmount(paymentTransactionDTO.getAmount());
        }

        if (paymentTransactionDTO.getStatus() != null) {
            paymentTransaction.setStatus(paymentTransactionDTO.getStatus());
        }

        return paymentTransactionRepository.save(paymentTransaction);
    }

    @Override
    public void deletePaymentTransaction(Long id) {
        PaymentTransaction paymentTransaction = paymentTransactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PaymentTransaction.class, id));

        paymentTransactionRepository.delete(paymentTransaction);
    }

    @Override
    public PaymentTransaction getPaymentTransactionById(Long id) {
        return paymentTransactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PaymentTransaction.class, id));
    }

    @Override
    public List<PaymentTransaction> getPaymentTransactionsByBooking(Booking booking) {
        return paymentTransactionRepository.findByBooking(booking);
    }

    @Override
    public List<PaymentTransaction> getPaymentTransactionsByStatus(String status) {
        return paymentTransactionRepository.findByStatus(status);
    }

    @Override
    public List<PaymentTransaction> getAllPaymentTransactions() {
        return paymentTransactionRepository.findAll();
    }

    @Override
    public void updatePaymentStatus(Long id, String status) {
        PaymentTransaction paymentTransaction = paymentTransactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PaymentTransaction.class, id));

        paymentTransaction.setStatus(status);

        // If the payment is successful, update the booking status
        if (status.equals("COMPLETED")) {
            Booking booking = paymentTransaction.getBooking();
            booking.setStatus("CONFIRMED");
            bookingRepository.save(booking);
        }

        // If the payment is refunded, update the booking status
        if (status.equals("REFUNDED")) {
            Booking booking = paymentTransaction.getBooking();
            booking.setStatus("CANCELLED");
            bookingRepository.save(booking);
        }

        paymentTransactionRepository.save(paymentTransaction);
    }
}