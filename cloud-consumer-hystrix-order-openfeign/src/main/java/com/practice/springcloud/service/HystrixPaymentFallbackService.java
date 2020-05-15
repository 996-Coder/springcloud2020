package com.practice.springcloud.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author michael.zhang
 * @date 5/15/2020 11:08 AM
 * @desc
 */
@Component
@RequestMapping("fallback/payment/hystrix")
public class HystrixPaymentFallbackService implements HystrixPaymentService {
    @Override
    public ResponseEntity<String> paymentOk(String id) {
        return ResponseEntity.ok("调用HystrixPaymentService.paymentOk方法失败，调用其fallback method");
    }

    @Override
    public ResponseEntity<String> paymentTimeout(String id) {
        return ResponseEntity.ok("调用HystrixPaymentService.paymentTimeout方法失败，调用其fallback method");
    }
}
