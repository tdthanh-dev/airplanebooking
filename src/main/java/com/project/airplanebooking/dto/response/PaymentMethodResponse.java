package com.project.airplanebooking.dto.response;

import java.time.LocalDateTime;

import com.project.airplanebooking.model.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodResponse {
    private Long id;
    private String name;
    private String type;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PaymentMethodResponse(PaymentMethod paymentMethod) {
        this.id = paymentMethod.getId();
        this.name = paymentMethod.getMethodName();
        this.type = paymentMethod.getDescription();
        this.isActive = true;
        this.createdAt = paymentMethod.getCreatedAt();
        this.updatedAt = paymentMethod.getUpdatedAt();
    }
}