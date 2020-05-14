package com.practice.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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
}

