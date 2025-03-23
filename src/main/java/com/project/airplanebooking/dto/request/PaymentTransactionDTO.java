package com.project.airplanebooking.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("booking_id")
    private Long bookingId;

    @NotNull(message = "Payment method ID is required")
    @JsonProperty("payment_method_id")
    private Long paymentMethodId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    @JsonProperty("amount")
    private Double amount;

    @NotNull(message = "Transaction date is required")
    @JsonProperty("transaction_date")
    private LocalDateTime transactionDate;

    @JsonProperty("status")
    private String status;
}