package com.example.demo.dtos;

public class LoginBodyDto {
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public LoginBodyDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginBodyDto setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "LoginBodyDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
