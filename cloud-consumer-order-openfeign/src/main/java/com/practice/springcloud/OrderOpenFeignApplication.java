package com.practice.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableFeignClients(defaultConfiguration = GlobalFeignConfiguration.class)
@EnableFeignClients
public class OrderOpenFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeignApplication.class, args);
    }
}
