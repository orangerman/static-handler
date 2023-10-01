package com.javafan.statichandler.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author javafan
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/api")
    public String testApi() {
        log.info("com.javafan.statichandler.controller.TestController.testApi");
        return "test";
    }

}
