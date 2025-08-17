package com.pahanaedu.billingsystem.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123"; // your chosen password
        String hashedPassword = encoder.encode(rawPassword);
        System.out.println("Hashed Password: " + hashedPassword);
    }
}
