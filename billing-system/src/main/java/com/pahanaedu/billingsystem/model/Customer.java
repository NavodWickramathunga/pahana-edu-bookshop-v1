package com.pahanaedu.billingsystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public class Customer {

    @Id
    private Long id;

    private String username; // Add this field
    private String password; // Add this field
    private String name;
    private String email;
    private String phone;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; } // Add this getter
    public void setUsername(String username) { this.username = username; } // Add this setter

    public String getPassword() { return password; } // Add this getter
    public void setPassword(String password) { this.password = password; } // Add this setter

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
