package com.practice.springcloud.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author michael.zhang
 * @date 6/12/2020 10:50
 * @desc
 */
@ConfigurationProperties("formatter")
public class DateFormatProperties {
    /**
     * default format pattern
     */
    private String pattern = "yyyy-MM-dd hh:mm:ss";

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
