package com.practice.springcloud.service;

import com.practice.springcloud.configuration.FeignConfiguration;
import com.practice.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotEmpty;

@Service
//@FeignClient(value = "PAYMENT-SERVICE")
@FeignClient(value = "PAYMENT-SERVICE", configuration = FeignConfiguration.class)
@RequestMapping("/payment")
public interface PaymentFeignService {
    @GetMapping("/{id}")
    ResponseEntity<Payment> getById(@NotEmpty @PathVariable String id);

    @GetMapping("/openfeign/timeout")
    ResponseEntity<String> openFeignTimeout();
}
