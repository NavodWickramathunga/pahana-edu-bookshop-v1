package com.pahanaedu.billingsystem.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pahanaedu.billingsystem.model.Item;
import com.pahanaedu.billingsystem.service.ItemService;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*")  // Adjust origins as needed
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Item> addItem(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("stock") int stock,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) {
        try {
            Item item = new Item();
            item.setName(name);
            item.setPrice(price);
            item.setStock(stock);

            if (imageFile != null && !imageFile.isEmpty()) {
                String imageUrl = itemService.saveImageAndGetUrl(imageFile);
                item.setImageUrl(imageUrl);
            }

            Item savedItem = itemService.saveItem(item);
            return ResponseEntity.ok(savedItem);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
