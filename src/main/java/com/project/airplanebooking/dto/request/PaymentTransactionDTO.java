package com.project.airplanebooking.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransactionDTO {
    @NotNull(message = "Booking ID is required")
    private Long bookingId;

    @NotNull(message = "Payment method ID is required")
    private Long paymentMethodId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotNull(message = "Transaction date is required")
    private LocalDateTime transactionDate;

    private String status;
}