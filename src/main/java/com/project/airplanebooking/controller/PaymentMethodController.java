package com.project.airplanebooking.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.airplanebooking.dto.request.PaymentMethodDTO;
import com.project.airplanebooking.dto.response.PaymentMethodResponse;
import com.project.airplanebooking.model.PaymentMethod;
import com.project.airplanebooking.service.impl.PaymentMethodServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/payment-methods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodServiceImpl paymentMethodServiceImpl;

    @PostMapping("/")
    public ResponseEntity<?> createPaymentMethod(@Valid @RequestBody PaymentMethodDTO paymentMethodDTO) {
        try {
            PaymentMethod paymentMethod = paymentMethodServiceImpl.createPaymentMethod(paymentMethodDTO);
            return ResponseEntity.ok(new PaymentMethodResponse(paymentMethod));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllPaymentMethods() {
        try {
            List<PaymentMethod> paymentMethods = paymentMethodServiceImpl.getAllPaymentMethods();
            List<PaymentMethodResponse> responseList = paymentMethods.stream()
                    .map(PaymentMethodResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentMethodById(@PathVariable Long id) {
        try {
            PaymentMethod paymentMethod = paymentMethodServiceImpl.getPaymentMethodById(id);
            return ResponseEntity.ok(new PaymentMethodResponse(paymentMethod));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePaymentMethod(@PathVariable Long id,
            @Valid @RequestBody PaymentMethodDTO paymentMethodDTO) {
        try {
            PaymentMethod paymentMethod = paymentMethodServiceImpl.updatePaymentMethod(id, paymentMethodDTO);
            return ResponseEntity.ok(new PaymentMethodResponse(paymentMethod));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaymentMethod(@PathVariable Long id) {
        try {
            paymentMethodServiceImpl.deletePaymentMethod(id);
            return ResponseEntity.ok("Payment method deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}