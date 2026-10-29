package com.gridpulse.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gridpulse.auth.dto.LoginRequest;
import com.gridpulse.auth.dto.LoginResponse;
import com.gridpulse.auth.dto.RegisterRequest;
import com.gridpulse.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Register user
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request) {

        authService.register(request);

        return ResponseEntity.ok("User registered successfully");
    }

    // Login user and generate JWT
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    // Protected test API
    @GetMapping("/test")
    public ResponseEntity<String> test() {

        return ResponseEntity.ok("JWT Authentication Working");
    }
}