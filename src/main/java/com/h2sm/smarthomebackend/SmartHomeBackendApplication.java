package com.h2sm.smarthomebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan(basePackages = "com.h2sm.smarthomebackend.api")
public class SmartHomeBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartHomeBackendApplication.class, args);
    }
}
