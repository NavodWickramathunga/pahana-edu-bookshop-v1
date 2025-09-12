package com.pahanaedu.billingsystem.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pahanaedu.billingsystem.service.ImageService;

@RestController
@RequestMapping("/api")
public class BannerController {

    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/upload-banner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<Map<String, String>> uploadBannerImage(@RequestParam("bannerImage") MultipartFile file) {
    try {
        // Call your service method that may throw IOException or IllegalArgumentException (example)
        String imageUrl = imageService.resizeAndSaveImage(file, 1200, 400);
        return ResponseEntity.ok(Map.of(
                "message", "Banner image uploaded and resized successfully!",
                "imageUrl", imageUrl
        ));
    } catch (IOException | IllegalArgumentException e) {
        // Handle specific exceptions in one catch block
        return ResponseEntity.status(500).body(Map.of(
                "message", "Failed to process image: " + e.getMessage()
        ));
    }
}

}
