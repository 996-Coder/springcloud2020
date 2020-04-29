package com.practice.springcloud.controller;

import com.google.gson.Gson;
import com.practice.springcloud.entity.Payment;
import com.practice.springcloud.loadbalance.MyLoadBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/consumer")
public class OrderController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MyLoadBalance myLoadBalance;

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/{id}")
    public ResponseEntity get(@NotEmpty @PathVariable String id) {
        // 获取所有payment-service实例
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("payment-service");
        // 传入服务实例列表，得到本次将要调用的实例
        ServiceInstance instance = myLoadBalance.instance(serviceInstances);

        ResponseEntity responseEntity = restTemplate.getForEntity(instance.getUri() + "/payment/" + id, Payment.class);
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
