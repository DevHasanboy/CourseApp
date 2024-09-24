package com.example.file.courseapp.controller;

import com.example.file.courseapp.service.VideoService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/video")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;


    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
                    mediaType = "multipart/form-data",
                    schema = @Schema(type = "string", format = "binary")))
            @RequestParam("file") MultipartFile file) {
        try {
            String fileName = videoService.uploadVideo(file);
            return ResponseEntity.ok("Fayl muvaffaqiyatli yuklandi: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fayl yuklashda xatolik: " + e.getMessage());
        }
    }
}
