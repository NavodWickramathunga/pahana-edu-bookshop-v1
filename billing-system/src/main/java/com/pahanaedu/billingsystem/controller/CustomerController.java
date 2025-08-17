package com.pahanaedu.billingsystem.controller;

import com.pahanaedu.billingsystem.model.User;
import com.pahanaedu.billingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody User customer,
                                         @RequestParam String mobile,
                                         @RequestParam String password) {
        if (!userService.authenticateAdmin(mobile, password)) {
            return ResponseEntity.status(403).body("Access Denied: Admins only");
        }
        // Save customer logic (reuse registerUser for simplicity)
        return ResponseEntity.ok(userService.registerUser(customer));
    }
}
