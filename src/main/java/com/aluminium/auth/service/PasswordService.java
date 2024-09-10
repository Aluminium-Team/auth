package com.aluminium.auth.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordService {
    private final PasswordEncoder passwordEncoder;

    public PasswordService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Method to encrypt a plain text password
    public String encryptPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    // Method to verify if a plain text password matches the encrypted password
    public boolean matchesPassword(String plainPassword, String encryptedPassword) {
        return passwordEncoder.matches(plainPassword, encryptedPassword);
    }
}
