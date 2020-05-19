package com.practice.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael.zhang
 * @date 5/19/2020 4:42 PM
 * @desc
 */
@RestController
public class ConfigInfoController {
    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

    @GetMapping("/config-info")
    public String env() {
        return configInfo;
    }

    @Value("${config.info}")
    String configInfo;
}
