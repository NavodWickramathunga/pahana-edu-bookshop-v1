package com.pahanaedu.billingsystem.controller;

import com.pahanaedu.billingsystem.model.Item;
import com.pahanaedu.billingsystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        return itemService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return "Item deleted successfully!";
    }
}
