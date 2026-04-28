package com.internship.internshipmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/auth/health")
    public String test() {
        return "Backend is running";
    }
}
