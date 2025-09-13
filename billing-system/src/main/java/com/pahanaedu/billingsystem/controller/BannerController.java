package com.pahanaedu.billingsystem.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pahanaedu.billingsystem.service.BannerService;
import com.pahanaedu.billingsystem.service.ImageService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:8000", "http://localhost:8080", "http://127.0.0.1:8080", "http://127.0.0.1:8000"})
public class BannerController {

    private static final Logger logger = LoggerFactory.getLogger(BannerController.class);
    
    private final ImageService imageService;
    private final BannerService bannerService;

    public BannerController(ImageService imageService, BannerService bannerService) {
        this.imageService = imageService;
        this.bannerService = bannerService;
    }

    // Upload and resize the banner to 1200x400, then store the latest URL
    @PostMapping(value = "/banner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadBannerImage(@RequestParam("banner") MultipartFile file) {
        logger.info("Banner upload request received. File: {}, Size: {}", 
                   file != null ? file.getOriginalFilename() : "null", 
                   file != null ? file.getSize() : 0);
        
        try {
            if (file == null || file.isEmpty()) {
                logger.warn("No image file provided in banner upload request");
                return ResponseEntity.badRequest().body(Map.of("message", "No image file provided."));
            }

            logger.info("Processing banner image: {} ({} bytes)", file.getOriginalFilename(), file.getSize());
            String imageUrl = imageService.resizeAndSaveImage(file, 1200, 400);
            logger.info("Banner image processed successfully. URL: {}", imageUrl);
            
            bannerService.saveCurrentBannerUrl(imageUrl);
            logger.info("Banner URL saved to metadata file");

            return ResponseEntity.ok(Map.of(
                    "message", "Banner image uploaded and resized successfully!",
                    "imageUrl", imageUrl
            ));
        } catch (IOException e) {
            logger.error("IO error processing banner upload", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to process image: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            logger.error("Invalid argument during banner upload", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Invalid image file: " + e.getMessage()));
        } catch (Exception e) {
            logger.error("Unexpected error during banner upload", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Unexpected error: " + e.getMessage()));
        }
    }

    // Return the latest saved banner URL
    @GetMapping("/banner")
    public ResponseEntity<Map<String, String>> getCurrentBanner() {
        logger.info("Banner retrieval request received");
        try {
            Optional<String> urlOpt = bannerService.loadCurrentBannerUrl();
            if (urlOpt.isPresent()) {
                logger.info("Banner URL found: {}", urlOpt.get());
                return ResponseEntity.ok(Map.of("imageUrl", urlOpt.get()));
            }
            logger.info("No banner URL found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No banner uploaded yet."));
        } catch (IOException e) {
            logger.error("Error loading banner URL", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to load banner URL: " + e.getMessage()));
        }
    }
}