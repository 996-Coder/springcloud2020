package com.practice.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author michael.zhang
 * @date 5/14/2020 2:47 PM
 * @desc
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix // enable Hystrix circuit breakers
public class HystrixOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixOrderApplication.class, args);
    }
}
