package com.practice.ribbon.rule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomRule {
    @Bean
    public IRule randomRule(){
        return new com.netflix.loadbalancer.RandomRule();
    }
}
