package com.pahanaedu.billingsystem.controller;

import com.pahanaedu.billingsystem.model.Item;
import com.pahanaedu.billingsystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getItemsInStock() {
        List<Item> items = itemService.getAllItems();
        items.removeIf(item -> item.getStock() == null || item.getStock() <= 0);
        return ResponseEntity.ok(items);
    }
}