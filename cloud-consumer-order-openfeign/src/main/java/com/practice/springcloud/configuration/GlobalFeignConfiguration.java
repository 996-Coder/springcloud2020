package com.practice.springcloud.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class GlobalFeignConfiguration {
    /**
     * NONE:不记录任何日志（默认值）
     * BASIC:仅记录请求方法、URL、响应状态码以及执行时间
     * HEADERS:记录BASIC级别的基础上，记录请求响应的header
     * FULL:记录请求和响应的header，body和元数据
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
