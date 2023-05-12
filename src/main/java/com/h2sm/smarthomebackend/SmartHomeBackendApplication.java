package com.h2sm.smarthomebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SmartHomeBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartHomeBackendApplication.class, args);
    }
}
