package com.javafan.statichandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StaticHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaticHandlerApplication.class, args);
    }

}
