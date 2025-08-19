package com.pahanaedu.billingsystem.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword; // This should be a hashed password

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Admin Login
    @PostMapping("/admin/login")
    public ResponseEntity<Object> adminLogin(@RequestParam String username, @RequestParam String password) {
        if (adminUsername.equals(username) && passwordEncoder.matches(password, adminPassword)) {
            return ResponseEntity.ok(new ResponseMessage("✅ Admin login successful!"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("❌ Invalid Admin credentials!"));
    }

    // Response message class
    public static class ResponseMessage {
        private String message;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
