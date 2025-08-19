package com.pahanaedu.billingsystem.dto;

import com.pahanaedu.billingsystem.model.User;

public class AddCustomerRequest {
    private User customer;
    private String mobile;
    private String password;
    
    // getters and setters
    public User getCustomer() { return customer; }
    public void setCustomer(User customer) { this.customer = customer; }
    
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}