package com.example.file.courseapp.service;

import com.example.file.courseapp.dto.BlogDto;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;


public interface BlogService {

    ResponseEntity<?> create(BlogDto blog);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> update(BlogDto blog, Long id);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<?> getAll();

    ResponseEntity<?> getAllPage(Pageable pageable);
}
