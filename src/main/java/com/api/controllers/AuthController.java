package com.api.controllers;

import com.api.dtos.SignUpDTO;
import com.api.dtos.SignInDTO;
import com.api.services.AuthService;
import com.api.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@Valid @RequestBody SignUpDTO signupRequest) {
        try {
            authService.registerUser(signupRequest);
            return ResponseEntity.ok("User created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody SignInDTO signinRequest) {
        try {
            String email = signinRequest.getEmail();
            authService.authenticateUser(signinRequest);
            String token = jwtService.generateToken(email);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("email", email);

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}
