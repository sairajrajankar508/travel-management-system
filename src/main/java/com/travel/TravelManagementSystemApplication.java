package com.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.travel")
public class TravelManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelManagementSystemApplication.class, args);
    }
}
