package com.practice.springcloud.service;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.netflix.hystrix.HystrixProperties;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author michael
 * @date 5/14/2020 11:35 AM
 */

@Service
public class PaymentService {
    public String paymentOk() {
        return "ok";
    }


    // 服务降级

    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentTimeout() {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int a = 15/0;
        System.out.println("请求完成");
        return "ok";
    }

    public String paymentTimeoutHandler() {
        return "provider程序运行超时或出现异常，调用provider的fallbackMethod";
    }


    //=====================================
    // 服务熔断

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerHandler", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 失败率阈值
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("Id can not be negative. ");
        }
        return "ok";
    }

    public String paymentCircuitBreakerHandler(Integer id) {
        return "provider程序运行超时或出现异常，调用provider的fallbackMethod";
    }

}

