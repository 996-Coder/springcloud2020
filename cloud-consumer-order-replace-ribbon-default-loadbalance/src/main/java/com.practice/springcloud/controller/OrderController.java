package com.practice.springcloud.controller;

import com.google.gson.Gson;
import com.practice.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/consumer")
public class OrderController {
    private final RestTemplate restTemplate;

//    private static final String PAYMENT_URL = "http://localhost:8001/payment";
    private static final String PAYMENT_URL = "http://payment-service/payment";

    @Autowired
    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Payment payment) {
        ResponseEntity responseEntity = restTemplate.postForEntity(PAYMENT_URL + "/create", payment, ResponseEntity.class);
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@NotEmpty @PathVariable String id) {
        ResponseEntity responseEntity = restTemplate.getForEntity(PAYMENT_URL + "/" + id, Payment.class);
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
