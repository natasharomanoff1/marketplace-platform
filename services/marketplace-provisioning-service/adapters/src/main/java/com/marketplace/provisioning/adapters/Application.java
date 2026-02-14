package com.marketplace.provisioning.adapters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.marketplace.provisioning",
        "com.marketplace.common.kafka",
        "com.marketplace.common.errors"
})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
