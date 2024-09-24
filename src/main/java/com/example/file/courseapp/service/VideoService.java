package com.example.file.courseapp.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoService {

    String uploadVideo(MultipartFile file) throws IOException;

}
