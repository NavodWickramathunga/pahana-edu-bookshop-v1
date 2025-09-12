package com.pahanaedu.billingsystem.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class BannerService {

    private static final Path UPLOAD_DIR = Paths.get("uploads");
    private static final Path META_FILE = UPLOAD_DIR.resolve("banner.txt");

    public void saveCurrentBannerUrl(String url) throws IOException {
        if (!Files.exists(UPLOAD_DIR)) {
            Files.createDirectories(UPLOAD_DIR);
        }
        Files.writeString(META_FILE, url == null ? "" : url.trim(), StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public Optional<String> loadCurrentBannerUrl() throws IOException {
        if (!Files.exists(META_FILE)) {
            return Optional.empty();
        }
        String content = Files.readString(META_FILE, StandardCharsets.UTF_8).trim();
        if (content.isEmpty()) return Optional.empty();
        return Optional.of(content);
    }
}