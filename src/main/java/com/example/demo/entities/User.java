package com.example.demo.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String name;

    @Column(unique = true)
    private String email;

    private String role;

    @JsonIgnore
    private String password;

    @Column(name = "password_changed_at")
    private Date passwordChangedAt;

    @Column(name = "password_reset_token")
    private String passwordResetToken;

    @Column(name = "password_reset_expires")
    private Date passwordResetExpires;

    // UserDetails interface methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + getRole().toUpperCase());

        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email; // Assuming email is used as username
    }

    @Override
    public boolean isAccountNonExpired() {
        // Implement your logic for account expiration status
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Implement your logic for account locking status
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Implement your logic for credentials expiration status
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Implement your logic for account enabled status
        return true;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getPasswordChangedAt() {
        return this.passwordChangedAt;
    }

    public void setPasswordChangedAt(Date passwordChangedAt) {
        this.passwordChangedAt = passwordChangedAt;
    }

    public String getPasswordResetToken() {
        return this.passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public Date getPasswordResetExpires() {
        return this.passwordResetExpires;
    }

    public void setPasswordResetExpires(Date passwordResetExpires) {
        this.passwordResetExpires = passwordResetExpires;
    }

    public User id(UUID id) {
        setId(id);
        return this;
    }

    public User name(String name) {
        setName(name);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User role(String role) {
        setRole(role);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User passwordChangedAt(Date passwordChangedAt) {
        setPasswordChangedAt(passwordChangedAt);
        return this;
    }

    public User passwordResetToken(String passwordResetToken) {
        setPasswordResetToken(passwordResetToken);
        return this;
    }

    public User passwordResetExpires(Date passwordResetExpires) {
        setPasswordResetExpires(passwordResetExpires);
        return this;
    }

}
