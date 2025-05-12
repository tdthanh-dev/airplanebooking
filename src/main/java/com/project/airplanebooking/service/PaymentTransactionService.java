package com.project.airplanebooking.service;

import com.project.airplanebooking.dto.request.PaymentTransactionDTO;
import com.project.airplanebooking.model.PaymentTransaction;
import com.project.airplanebooking.model.Booking;

import java.util.List;

public interface PaymentTransactionService {
    PaymentTransaction createPaymentTransaction(PaymentTransactionDTO paymentTransactionDTO);

    PaymentTransaction updatePaymentTransaction(Long id, PaymentTransactionDTO paymentTransactionDTO);

    void deletePaymentTransaction(Long id);

    PaymentTransaction getPaymentTransactionById(Long id);

    List<PaymentTransaction> getPaymentTransactionsByBooking(Booking booking);

    List<PaymentTransaction> getPaymentTransactionsByStatus(String status);

    List<PaymentTransaction> getAllPaymentTransactions();

    void updatePaymentStatus(Long id, String status);
}