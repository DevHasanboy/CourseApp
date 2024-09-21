package com.example.file.courseapp.controller;

import com.example.file.courseapp.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @Value("${spring.project.poster}")
    private String path;


    @Operation(summary = "to upload the file", description = "file uploaded", tags = "CREATE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "file upload successfully "),
            @ApiResponse(responseCode = "404", description = " file not found")
    })
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file) throws IOException {
        String s = fileService.uploadFile(path, file);
        return ResponseEntity.status(HttpStatus.OK).body("file upload successfully" + s);

    }

    @Operation(summary = "to download the file", description = "file download", tags = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "file download successfully "),
            @ApiResponse(responseCode = "404", description = " file not found")
    })
    @GetMapping("/download")
    public void downloadFile(@RequestParam String fileName, HttpServletResponse response) throws IOException {
        InputStream resourceFile = fileService.downloadFile(path, fileName);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(resourceFile, response.getOutputStream());

    }
}
