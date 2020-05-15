package com.practice.springcloud.controller;

import com.practice.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/payment/hystrix")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/ok/{id}")
    public ResponseEntity ok(@PathVariable String id) {
        return ResponseEntity.status(200).body(Thread.currentThread().getName() + ", id: " + id + ", " + paymentService.paymentOk());
    }

    @GetMapping("/timeout/{id}")
    public ResponseEntity timeout(@PathVariable String id) {
        log.info("请求进入consumer");
        return ResponseEntity.status(200).body(Thread.currentThread().getName() + ", id: " + id + ", " + paymentService.paymentTimeout());
    }
    @GetMapping("/circuit/{id}")
    public ResponseEntity paymentCircuitBreaker(@PathVariable String id) {
        log.info("请求进入consumer");
        return ResponseEntity.status(200).body(Thread.currentThread().getName() + ", id: " + id + ", " + paymentService.paymentCircuitBreaker(Integer.parseInt(id)));
    }


}
