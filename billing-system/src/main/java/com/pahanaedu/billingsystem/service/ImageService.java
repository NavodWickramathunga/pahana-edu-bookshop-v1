package com.pahanaedu.billingsystem.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@Service
public class ImageService {

    private static final String UPLOAD_DIR = "uploads";

    // Resizes to target size using a center-crop cover strategy and saves as JPEG
    public String resizeAndSaveImage(MultipartFile file, int targetWidth, int targetHeight) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Empty image file.");
        }

        File uploadPath = new File(UPLOAD_DIR);
        if (!uploadPath.exists()) {
            boolean created = uploadPath.mkdirs();
            if (!created) {
                throw new IOException("Unable to create upload directory.");
            }
        }

        String filename = "banner-" + UUID.randomUUID() + ".jpg";
        File destFile = new File(uploadPath, filename);

        Thumbnails.of(file.getInputStream())
                .size(targetWidth, targetHeight)
                .crop(Positions.CENTER)      // center-crop to maintain aspect ratio
                .outputFormat("jpg")
                .outputQuality(0.9)
                .toFile(destFile);

        // Return web-accessible URL (served via WebConfig mapping /uploads/**)
        return "/uploads/" + filename;
    }
}