package com.practice.springcloud.controller;

import com.practice.springcloud.entity.Payment;
import com.practice.springcloud.entity.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.practice.springcloud.service.PaymentService;

import javax.validation.constraints.NotEmpty;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody Payment payment) {
        Payment savedPayment = paymentService.insert(payment);
        if (savedPayment != null) {
            return ResponseEntity.ok(savedPayment);
        } else {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@NotEmpty @PathVariable String id) {
        paymentService.deleteById(id);
        return ResponseEntity.status(200).body("The payment with id: "+id +"has been deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Payment payment, @NotEmpty @PathVariable String id) {
        Payment savedPayment = paymentService.save(payment);
        return ResponseEntity.ok(savedPayment);
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/{id}")
    public ResponseEntity get(@NotEmpty @PathVariable String id) {
        Payment foundPayment = paymentService.getById(id);
        System.out.println(port);
        return ResponseEntity.ok(foundPayment);
    }

    @GetMapping("/openfeign/timeout")
    public ResponseEntity openFeignTimeout() {
        ResponseEntity responseEntity = new ResponseEntity(port, HttpStatus.OK);
        System.out.println(responseEntity.getBody());
        return responseEntity;
    }
}
