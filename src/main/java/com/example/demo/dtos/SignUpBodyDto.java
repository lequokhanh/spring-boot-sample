package com.example.demo.dtos;

public class SignUpBodyDto {
    private String email;

    private String password;

    private String name;

    public String getEmail() {
        return email;
    }

    public SignUpBodyDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SignUpBodyDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public SignUpBodyDto setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "SignUpBodyDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
