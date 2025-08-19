package com.pahanaedu.billingsystem.controller;

import com.pahanaedu.billingsystem.model.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bill")
public class BillController {

    @PostMapping
    public ResponseEntity<Map<String, Object>> calculateBill(@RequestBody List<Item> items) {
        double total = items.stream().mapToDouble(i -> i.getPrice() * i.getStock()).sum();

        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("total", total);

        return ResponseEntity.ok(response);
    }
}
