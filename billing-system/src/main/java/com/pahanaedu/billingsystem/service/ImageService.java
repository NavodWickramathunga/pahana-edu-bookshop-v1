package com.pahanaedu.billingsystem.service;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {

    private final String uploadDir = "uploads/";

    /**
     * Resize the image to targetWidth x targetHeight and save it to uploads folder.
     * Returns the relative URL path to the saved image.
     */
    public String resizeAndSaveImage(MultipartFile file, int targetWidth, int targetHeight) throws IOException {
        // Ensure upload directory exists
        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        // Generate unique filename with .jpg extension
        String filename = UUID.randomUUID().toString() + ".jpg";
        File destFile = new File(uploadPath, filename);

        // Use Thumbnailator to resize and save as JPEG
        Thumbnails.of(file.getInputStream())
                .size(targetWidth, targetHeight)
                .crop(net.coobird.thumbnailator.geometry.Positions.CENTER) // Crop center to maintain aspect ratio
                .outputFormat("jpg")
                .outputQuality(0.9)
                .toFile(destFile);

        // Return URL path accessible by frontend
        return "/uploads/" + filename;
    }
}
