package com.example.file.courseapp.service;

import com.example.file.courseapp.dto.TeacherDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TeacherService {

    ResponseEntity<?> createTeacher(TeacherDto teacher);

    ResponseEntity<?> updateTeacher(TeacherDto teacher, Long id);

    ResponseEntity<?> deleteTeacher(Long id);

    ResponseEntity<?> findTeacherById(Long id);

    ResponseEntity<?> findAllTeachers();

    ResponseEntity<?> getAllPage(Pageable pageable);
}
