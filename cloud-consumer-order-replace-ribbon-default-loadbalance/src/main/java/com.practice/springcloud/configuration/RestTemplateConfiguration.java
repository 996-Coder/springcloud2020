package com.practice.springcloud.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    @Bean
    @LoadBalanced // 使restTemplate具有负载均衡能力
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
