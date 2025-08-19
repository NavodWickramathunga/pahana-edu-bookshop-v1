package com.pahanaedu.billingsystem.controller;

import com.pahanaedu.billingsystem.model.Customer;
import com.pahanaedu.billingsystem.model.User;
import com.pahanaedu.billingsystem.repository.CustomerRepository;
import com.pahanaedu.billingsystem.service.CustomerService;
import com.pahanaedu.billingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    // ✅ Edit customer info
    @PutMapping("/edit/{id}")
    public ResponseEntity<Customer> editCustomer(@PathVariable String id, @RequestBody Customer customer) {
        Optional<Customer> updatedCustomer = customerService.updateCustomer(id, customer);
        return updatedCustomer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Get customer info
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String id) {
        return customerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Admin adds new customer
    @PostMapping("/add")
    public ResponseEntity<String> addCustomer(
            @RequestBody User customer,
            @RequestParam String adminMobile,
            @RequestParam String adminPassword) {

        if (!userService.authenticateAdmin(adminMobile, adminPassword)) {
            return ResponseEntity.status(403).body("Access Denied: Admins only");
        }

        if (userService.userExists(customer.getMobile())) {
            return ResponseEntity.badRequest().body("Customer already exists");
        }

        userService.registerUser(customer);
        return ResponseEntity.ok("Customer added successfully");
    }
}
