package com.pahanaedu.billingsystem.controller;

import com.pahanaedu.billingsystem.service.BannerService;
import com.pahanaedu.billingsystem.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BannerController {

    private final ImageService imageService;
    private final BannerService bannerService;

    public BannerController(ImageService imageService, BannerService bannerService) {
        this.imageService = imageService;
        this.bannerService = bannerService;
    }

    // Upload and resize the banner to 1200x400, then store the latest URL
    @PostMapping(value = "/upload-banner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadBannerImage(@RequestParam("bannerImage") MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "No image file provided."));
            }

            String imageUrl = imageService.resizeAndSaveImage(file, 1200, 400);
            bannerService.saveCurrentBannerUrl(imageUrl);

            return ResponseEntity.ok(Map.of(
                    "message", "Banner image uploaded and resized successfully!",
                    "imageUrl", imageUrl
            ));
        } catch (IOException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to process image: " + e.getMessage()));
        }
    }

    // Return the latest saved banner URL
    @GetMapping("/banner")
    public ResponseEntity<Map<String, String>> getCurrentBanner() {
        try {
            Optional<String> urlOpt = bannerService.loadCurrentBannerUrl();
            if (urlOpt.isPresent()) {
                return ResponseEntity.ok(Map.of("imageUrl", urlOpt.get()));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No banner uploaded yet."));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to load banner URL: " + e.getMessage()));
        }
    }
}