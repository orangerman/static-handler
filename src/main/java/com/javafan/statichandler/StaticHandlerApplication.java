package com.javafan.statichandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.only4play","com.javafan"})
public class StaticHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaticHandlerApplication.class, args);
    }

}
