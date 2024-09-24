package com.example.file.courseapp.impl;

import com.example.file.courseapp.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {


    @Value("${video.upload.dir}")
    private String uploadDir;

    @Override
    public String uploadVideo(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Fayl bo'sh!");
        }
        Path directory = Paths.get(uploadDir);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }

        Path path = directory.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return file.getOriginalFilename();
    }

}
