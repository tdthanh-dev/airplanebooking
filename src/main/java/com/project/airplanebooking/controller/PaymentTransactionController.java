package com.project.airplanebooking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.airplanebooking.dto.request.PaymentTransactionDTO;
import com.project.airplanebooking.dto.response.PaymentTransactionResponse;
import com.project.airplanebooking.model.Booking;
import com.project.airplanebooking.model.PaymentTransaction;
import com.project.airplanebooking.service.impl.BookingServiceImpl;
import com.project.airplanebooking.service.impl.PaymentTransactionServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/payment-transactions")
public class PaymentTransactionController {

    @Autowired
    private PaymentTransactionServiceImpl paymentTransactionServiceImpl;

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @PostMapping("/")
    public ResponseEntity<?> createPaymentTransaction(@Valid @RequestBody PaymentTransactionDTO paymentTransactionDTO) {
        try {
            PaymentTransaction paymentTransaction = paymentTransactionServiceImpl
                    .createPaymentTransaction(paymentTransactionDTO);
            return ResponseEntity.ok(new PaymentTransactionResponse(paymentTransaction));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllPaymentTransactions() {
        try {
            List<PaymentTransaction> paymentTransactions = paymentTransactionServiceImpl.getAllPaymentTransactions();
            List<PaymentTransactionResponse> responseList = paymentTransactions.stream()
                    .map(PaymentTransactionResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentTransactionById(@PathVariable Long id) {
        try {
            PaymentTransaction paymentTransaction = paymentTransactionServiceImpl.getPaymentTransactionById(id);
            return ResponseEntity.ok(new PaymentTransactionResponse(paymentTransaction));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> getPaymentTransactionsByBooking(@PathVariable Long bookingId) {
        try {
            Booking booking = bookingServiceImpl.getBookingById(bookingId);
            List<PaymentTransaction> paymentTransactions = paymentTransactionServiceImpl
                    .getPaymentTransactionsByBooking(booking);
            List<PaymentTransactionResponse> responseList = paymentTransactions.stream()
                    .map(PaymentTransactionResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getPaymentTransactionsByStatus(@PathVariable String status) {
        try {
            List<PaymentTransaction> paymentTransactions = paymentTransactionServiceImpl
                    .getPaymentTransactionsByStatus(status);
            List<PaymentTransactionResponse> responseList = paymentTransactions.stream()
                    .map(PaymentTransactionResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePaymentTransaction(@PathVariable Long id,
            @Valid @RequestBody PaymentTransactionDTO paymentTransactionDTO) {
        try {
            PaymentTransaction paymentTransaction = paymentTransactionServiceImpl.updatePaymentTransaction(id,
                    paymentTransactionDTO);
            return ResponseEntity.ok(new PaymentTransactionResponse(paymentTransaction));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaymentTransaction(@PathVariable Long id) {
        try {
            paymentTransactionServiceImpl.deletePaymentTransaction(id);
            return ResponseEntity.ok("Payment transaction deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            paymentTransactionServiceImpl.updatePaymentStatus(id, status);
            return ResponseEntity.ok("Payment status updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}