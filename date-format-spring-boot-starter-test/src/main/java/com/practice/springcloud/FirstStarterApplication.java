package com.practice.springcloud;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author michael.zhang
 * @date 6/12/2020 13:40
 * @desc
 */
@SpringBootApplication
public class FirstStarterApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(FirstStarterApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(simpleDateFormat.format(new Date()));
    }

    @Resource(type = SimpleDateFormat.class)
    private SimpleDateFormat simpleDateFormat;
}
