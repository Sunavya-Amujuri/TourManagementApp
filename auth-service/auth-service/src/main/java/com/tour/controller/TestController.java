package com.tour.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/user/test")
    public String test() {
        return "JWT Authentication Successful";
    }
}
