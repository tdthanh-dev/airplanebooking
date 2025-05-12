package com.project.airplanebooking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.project.airplanebooking.model.PaymentTransaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransactionResponse {
    private Long id;
    private String transactionReference;
    private BookingResponse booking;
    private PaymentMethodResponse paymentMethod;
    private BigDecimal amount;
    private String currency;
    private String status;
    private LocalDateTime paymentDate;
    private String paymentDetails;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PaymentTransactionResponse(PaymentTransaction transaction) {
        this.id = transaction.getId();
        this.transactionReference = "TXN" + transaction.getId();
        this.booking = new BookingResponse(transaction.getBooking());
        this.paymentMethod = new PaymentMethodResponse(transaction.getPaymentMethod());
        this.amount = BigDecimal.valueOf(transaction.getAmount());
        this.currency = "VND";
        this.status = transaction.getStatus();
        this.paymentDate = transaction.getTransactionDate();
        this.paymentDetails = "Payment for booking " + transaction.getBooking().getBookingReference();
        this.createdAt = transaction.getCreatedAt();
        this.updatedAt = transaction.getUpdatedAt();
    }
}