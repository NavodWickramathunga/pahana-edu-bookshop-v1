package com.pahanaedu.billingsystem.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pahanaedu.billingsystem.model.Item;
import com.pahanaedu.billingsystem.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    private final String uploadDir = "uploads/";

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public String saveImageAndGetUrl(MultipartFile imageFile) throws IOException {
        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        String originalFilename = imageFile.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        }
        String filename = UUID.randomUUID().toString() + extension;

        File destFile = new File(uploadPath, filename);
        imageFile.transferTo(destFile);

        return "/uploads/" + filename;
    }

    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }
}
