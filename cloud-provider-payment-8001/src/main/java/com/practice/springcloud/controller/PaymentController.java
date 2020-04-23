package com.practice.springcloud.controller;

import com.practice.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.practice.springcloud.service.PaymentService;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("create")
    public ResponseEntity create(@RequestBody Payment payment) {
        Payment savedPayment = paymentService.insert(payment);
        return ResponseEntity.ok(savedPayment);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity delete(@NotEmpty @PathVariable String id) {
        paymentService.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("update")
    public ResponseEntity update(@RequestBody Payment payment) {
        Payment savedPayment = paymentService.insert(payment);
        return ResponseEntity.ok(savedPayment);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity get(@NotEmpty @PathVariable String id) {
        Payment foundPayment = paymentService.getById(id);
        return ResponseEntity.ok(foundPayment);
    }
}
