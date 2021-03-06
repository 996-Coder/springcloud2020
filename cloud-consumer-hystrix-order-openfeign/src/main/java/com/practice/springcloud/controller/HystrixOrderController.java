package com.practice.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.practice.springcloud.service.HystrixPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael.zhang
 * @date 5/14/2020 3:02 PM
 * @desc
 */
@Slf4j
@RestController
@RequestMapping("/consumer/hystrix")
//@DefaultProperties(defaultFallback = "globalFallbackMethod", commandProperties = {
//        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
//})
public class HystrixOrderController {
    @Autowired
    HystrixPaymentService hystrixPaymentService;

    @GetMapping("/ok/{id}")
    public ResponseEntity paymentOk(@PathVariable String id) {
        return ResponseEntity.status(200).body(hystrixPaymentService.paymentOk(id).getBody());
    }

    @GetMapping("/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public ResponseEntity paymentTimeout(@PathVariable String id) {
        log.info("请求进入consumer");
        return ResponseEntity.status(200).body(hystrixPaymentService.paymentTimeout(id).getBody());
    }

    public ResponseEntity paymentTimeoutHandler(@PathVariable String id) {
        return ResponseEntity.status(200).body("provider响应过慢或consumer自身出现问题，调用consumer的fallbackMethod");
    }

    @HystrixCommand
    @GetMapping("/timeout1/{id}")
    public ResponseEntity paymentTimeout1(@PathVariable String id) {
        int a = 1 / 0;
        return ResponseEntity.status(200).body(hystrixPaymentService.paymentTimeout(id).getBody());
    }

    // 全局处理的方法不可以携带参数
    public ResponseEntity globalFallbackMethod() {
        return ResponseEntity.ok("Use global fallback method.");
    }
}
