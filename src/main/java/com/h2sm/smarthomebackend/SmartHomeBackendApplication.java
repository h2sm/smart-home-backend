package com.h2sm.smarthomebackend;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.h2sm.smarthomebackend.api")
public class SmartHomeBackendApplication {
    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(SmartHomeBackendApplication.class, args);
    }
}
