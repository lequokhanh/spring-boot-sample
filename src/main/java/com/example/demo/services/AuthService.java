package com.example.demo.services;

import com.example.demo.dtos.LoginBodyDto;
import com.example.demo.dtos.SignUpBodyDto;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(SignUpBodyDto body) {
        String email = body.getEmail();
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User()
                .email(email)
                .name(body.getName())
                .password(passwordEncoder.encode(body.getPassword()))
                .role("user");
        return userRepository.save(user);
    }

    public User authenticate(LoginBodyDto body) {
        String email = body.getEmail();
        String password = body.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

}