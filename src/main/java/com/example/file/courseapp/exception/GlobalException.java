package com.example.file.courseapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResponseEntity<?> exception(NotFoundException exception){
        return ResponseEntity.ok(exception.getMessage());
    }
}
