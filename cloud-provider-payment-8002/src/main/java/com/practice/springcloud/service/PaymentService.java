package com.practice.springcloud.service;

import com.practice.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.practice.springcloud.repository.PaymentRepository;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment insert(Payment payment) {
        return paymentRepository.insert(payment);
    }

    public void deleteById(String id) {
        paymentRepository.deleteById(id);
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment getById(String id) {
        return paymentRepository.findById(id).orElse(null);
    }
}
