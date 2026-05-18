package com.example.leadautomation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String home() {
        return "Server is working 🚀";
    }

    @GetMapping("/test")
    public String test() {
        return "API Working ✅";
    }
}