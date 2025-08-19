// src/main/java/com/pahanaedu/billingsystem/controller/AdminAuthController.java
package com.pahanaedu.billingsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminAuthController {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestParam String username, @RequestParam String password) {
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            return ResponseEntity.ok("Admin login successful!");
        }
        return ResponseEntity.status(401).body("Invalid admin credentials!");
    }
}

