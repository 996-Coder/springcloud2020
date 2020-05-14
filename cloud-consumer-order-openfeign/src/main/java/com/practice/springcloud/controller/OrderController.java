package com.practice.springcloud.controller;

import com.practice.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("consumer")
public class OrderController {
    @Autowired
    PaymentFeignService paymentFeignService;

    @GetMapping("/openfeign/timeout")
    public ResponseEntity<String> openFeignTimeout(){
        return ResponseEntity.ok(paymentFeignService.openFeignTimeout().getBody());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@NotEmpty @PathVariable String id) {
        return ResponseEntity.status(200).body(paymentFeignService.getById(id).getBody());
    }

}