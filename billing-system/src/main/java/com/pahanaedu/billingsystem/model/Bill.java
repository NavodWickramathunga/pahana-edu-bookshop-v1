package com.pahanaedu.billingsystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "bills")
public class Bill {
    @Id
    private Long id;
    private Long userId;
    private String username;
    private String email;
    private List<CartItem> items;
    private Double total;
    private Date date;

    public Bill() {}

    public Bill(Long userId, String username, String email, List<CartItem> items, Double total, Date date) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.items = items;
        this.total = total;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public Double getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }
}