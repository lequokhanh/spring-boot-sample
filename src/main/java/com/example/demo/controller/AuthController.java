package com.example.demo.controller;

import com.example.demo.dtos.LoginBodyDto;
import com.example.demo.dtos.SignUpBodyDto;
import com.example.demo.entities.User;
import com.example.demo.responses.LoginResponse;
import com.example.demo.services.AuthService;
import com.example.demo.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final JwtService jwtService;

    private final AuthService authenticationService;

    public AuthController(JwtService jwtService, AuthService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpBodyDto body) {
        User user = authenticationService.signup(body);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginBodyDto body) {
        User user = authenticationService.authenticate(body);
        String token = jwtService.generateToken(user);
        LoginResponse response = new LoginResponse()
                .setToken(token)
                .setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(response);
    }

    // @PostMapping("/forgotPassword")
    // @PostMapping("/resetPassword/{token}")
    // @PatchMapping("/updateMyPassword")
}