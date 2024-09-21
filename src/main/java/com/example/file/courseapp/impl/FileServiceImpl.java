package com.example.file.courseapp.impl;

import com.example.file.courseapp.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${spring.project.poster}")
    private String path;

    @Override
    public String uploadFile(String fileName, MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String filePath = path + File.separator + filename;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    @Override
    public InputStream downloadFile(String path, String fileName) throws FileNotFoundException {
        String filePath = path + File.separator + fileName;
        return new FileInputStream(filePath);
    }
}
