package com.practice.springcloud.service;

import com.practice.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotEmpty;

@Service
@FeignClient(value = "PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("/payment/{id}")
    ResponseEntity<Payment> get(@NotEmpty @PathVariable String id);
}
