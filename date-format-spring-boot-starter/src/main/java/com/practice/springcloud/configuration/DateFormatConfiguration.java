package com.practice.springcloud.configuration;

import com.practice.springcloud.domain.DateFormatProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author michael.zhang
 * @date 6/12/2020 10:45
 * @desc
 */
@Configuration
@EnableConfigurationProperties(DateFormatProperties.class)
@ConditionalOnProperty(prefix = "formatter", name = "enabled", havingValue = "true")
public class DateFormatConfiguration {
    private DateFormatProperties dateFormatProperties;
    public DateFormatConfiguration(DateFormatProperties dateFormatProperties) {
        this.dateFormatProperties = dateFormatProperties;
    }

    @Bean(name = "myDateFormatter")
    public SimpleDateFormat myDateFormatter() {
        System.out.println("start to initialize SimpleDateFormat with pattern: " + dateFormatProperties.getPattern());
        return new SimpleDateFormat(dateFormatProperties.getPattern());
    }

}
