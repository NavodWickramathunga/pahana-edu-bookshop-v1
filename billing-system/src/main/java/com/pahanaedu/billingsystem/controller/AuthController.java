package com.pahanaedu.billingsystem.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword; // This will hold the plain text password

    // Admin Login
    @PostMapping("/admin/login")
    public ResponseEntity<String> adminLogin(@RequestParam String username, @RequestParam String password) {
        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            return ResponseEntity.ok("✅ Admin login successful!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ Invalid Admin credentials!");
    }
}
