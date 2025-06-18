package com.project.airplanebooking.service.impl;

import com.project.airplanebooking.dto.request.PaymentMethodDTO;
import com.project.airplanebooking.exception.EntityNotFoundException;
import com.project.airplanebooking.model.PaymentMethod;
import com.project.airplanebooking.repository.PaymentMethodRepository;
import com.project.airplanebooking.service.PaymentMethodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public PaymentMethod createPaymentMethod(PaymentMethodDTO paymentMethodDTO) {

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setMethodName(paymentMethodDTO.getMethodName());
        paymentMethod.setDescription(paymentMethodDTO.getDescription());

        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public PaymentMethod updatePaymentMethod(Long id, PaymentMethodDTO paymentMethodDTO) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PaymentMethod.class, id));

        paymentMethod.setMethodName(paymentMethodDTO.getMethodName());
        paymentMethod.setDescription(paymentMethodDTO.getDescription());

        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public void deletePaymentMethod(Long id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PaymentMethod.class, id));

        paymentMethodRepository.delete(paymentMethod);
    }

    @Override
    public PaymentMethod getPaymentMethodById(Long id) {
        return paymentMethodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PaymentMethod.class, id));
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

}