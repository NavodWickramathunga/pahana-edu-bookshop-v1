package com.pahanaedu.billingsystem.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pahanaedu.billingsystem.model.Item;
import com.pahanaedu.billingsystem.service.ItemService;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        logger.info("Getting all items");
        List<Item> items = itemService.getAllItems();
        logger.info("Found {} items", items.size());
        return ResponseEntity.ok(items);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Item> addItem(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("stock") int stock,
            @RequestParam("author") String author,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) {
        logger.info("Adding new item: name={}, price={}, stock={}, author={}, hasImage={}", 
                   name, price, stock, author, imageFile != null && !imageFile.isEmpty());
        
        try {
            Item item = new Item();
            item.setName(name);
            item.setPrice(price);
            item.setStock(stock);
            item.setAuthor(author);

            if (imageFile != null && !imageFile.isEmpty()) {
                logger.info("Processing image file: {} ({} bytes)", imageFile.getOriginalFilename(), imageFile.getSize());
                String imageUrl = itemService.saveImageAndGetUrl(imageFile);
                item.setImageUrl(imageUrl);
                logger.info("Image saved with URL: {}", imageUrl);
            }

            Item savedItem = itemService.saveItem(item);
            logger.info("Item saved successfully with ID: {}", savedItem.getId());
            return ResponseEntity.ok(savedItem);
        } catch (IOException e) {
            logger.error("Error saving item image", e);
            return ResponseEntity.status(500).build();
        } catch (Exception e) {
            logger.error("Unexpected error adding item", e);
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        logger.info("Deleting item with ID: {}", id);
        try {
            itemService.deleteItem(id);
            logger.info("Item deleted successfully");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error deleting item with ID: {}", id, e);
            return ResponseEntity.status(500).build();
        }
    }
}
