package com.practice.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author michael.zhang
 * @date 5/14/2020 2:51 PM
 * @desc
 */
@Service
@FeignClient(value = "HYSTRIX-PAYMENT-SERVICE")
@RequestMapping("/payment/hystrix")
public interface HystrixOrderService {
    @GetMapping("/ok/{id}")
    ResponseEntity<String> paymentOk(@PathVariable String id);

    @GetMapping("/timeout/{id}")
    ResponseEntity<String> paymentTimeout(@PathVariable String id);
}
