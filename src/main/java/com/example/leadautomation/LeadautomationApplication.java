package com.example.leadautomation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LeadautomationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeadautomationApplication.class, args);
    }
}
