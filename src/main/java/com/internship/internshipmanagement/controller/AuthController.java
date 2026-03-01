package com.internship.internshipmanagement.controller;

import com.internship.internshipmanagement.dto.AuthResponse;
import com.internship.internshipmanagement.dto.LoginRequest;
import com.internship.internshipmanagement.dto.RegisterRequest;
import com.internship.internshipmanagement.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @GetMapping
    public String test() {
        return "Auth Controller Working ✅";
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
