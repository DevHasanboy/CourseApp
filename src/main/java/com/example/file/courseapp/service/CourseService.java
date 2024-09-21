package com.example.file.courseapp.service;

import com.example.file.courseapp.dto.CourseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface CourseService {

    ResponseEntity<?> create(CourseDto dto);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> update(CourseDto dto, Long id);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<?> getAll();

    ResponseEntity<?> getAllPage(Pageable pageable);

}
