package com.project.airplanebooking.service;

import com.project.airplanebooking.dto.request.PaymentMethodDTO;
import com.project.airplanebooking.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {
    PaymentMethod createPaymentMethod(PaymentMethodDTO paymentMethodDTO);

    PaymentMethod updatePaymentMethod(Long id, PaymentMethodDTO paymentMethodDTO);

    void deletePaymentMethod(Long id);

    PaymentMethod getPaymentMethodById(Long id);

    List<PaymentMethod> getAllPaymentMethods();

}