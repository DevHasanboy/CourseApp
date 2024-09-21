package com.example.file.courseapp.impl;

import com.example.file.courseapp.dto.CourseDto;
import com.example.file.courseapp.entity.Course;
import com.example.file.courseapp.entity.Teacher;
import com.example.file.courseapp.exception.NotFoundException;
import com.example.file.courseapp.repository.CourseRepository;
import com.example.file.courseapp.repository.TeacherRepository;
import com.example.file.courseapp.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public ResponseEntity<?> create(CourseDto dto) {
        Course course = new Course();
        course.setCost(dto.getCost());
        course.setName(dto.getName());
        course.setLanguage(String.valueOf(Locale.ENGLISH));
        course.setCreatedAt(LocalDate.now());
        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body("course create successfully");
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Course course = this.courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("course not found"));

        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setCost(course.getCost());
        dto.setName(course.getName());
        dto.setLanguage(course.getLanguage());
        List<Teacher> byCourseId = this.teacherRepository.findByCourseId(id);
        if (!byCourseId.isEmpty()) {
            dto.setTeacherList(byCourseId);
        }
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Override
    public ResponseEntity<?> update(CourseDto dto, Long id) {
        Course course = this.courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("course not found"));
        course.setName(dto.getName());
        course.setCost(dto.getCost());
        course.setLanguage(String.valueOf(Locale.ENGLISH));
        course.setUpdateAt(LocalDate.now());
        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.OK).body("course updated successfully");
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Course course = this.courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("course not found"));
        courseRepository.delete(course);
        return ResponseEntity.status(HttpStatus.OK).body("course deleted successfully");
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Course> all = this.courseRepository.findAll();
        if (!all.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(all);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("list is empty");
    }

    @Override
    public ResponseEntity<?> getAllPage(Pageable pageable) {
        List<Course> courses = this.courseRepository.findAll();
        if (!courses.isEmpty()) {
            List<CourseDto> dtos = courses.stream()
                    .map(course ->
                            new CourseDto(
                                    course.getId(),
                                    course.getName(),
                                    course.getCost(),
                                    course.getLanguage(),
                                    course.getContactId(),
                                    course.getTeacherList(),
                                    course.getCreatedAt(),
                                    course.getUpdateAt()
                            )).toList();

            return ResponseEntity.status(HttpStatus.OK).body(dtos);

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("list is empty");
    }
}
