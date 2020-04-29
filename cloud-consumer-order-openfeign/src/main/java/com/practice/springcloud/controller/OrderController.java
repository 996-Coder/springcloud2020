package com.practice.springcloud.controller;

import com.google.gson.Gson;
import com.practice.springcloud.entity.Payment;
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

    @GetMapping("/{id}")
    public ResponseEntity get(@NotEmpty @PathVariable String id) {

        ResponseEntity responseEntity= paymentFeignService.get(id);
        System.out.println(responseEntity.getBody());
        return ResponseEntity.status(200).body(responseBodyToPayment(responseEntity));
    }

    private static Payment responseBodyToPayment(ResponseEntity responseEntity) {
        if (responseEntity == null) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(responseEntity.getBody()), Payment.class);
    }

}