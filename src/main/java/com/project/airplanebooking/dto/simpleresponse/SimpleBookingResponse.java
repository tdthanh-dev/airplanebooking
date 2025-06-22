package com.project.airplanebooking.dto.simpleresponse;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleBookingResponse {
    private Long id;
    private String bookingReference;
    private String status;
    private BigDecimal totalPrice;
    private String message = "Đặt vé thành công";
}