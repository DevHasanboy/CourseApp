package com.example.file.courseapp.impl;

import com.example.file.courseapp.dto.TeacherDto;
import com.example.file.courseapp.entity.Teacher;
import com.example.file.courseapp.exception.NotFoundException;
import com.example.file.courseapp.repository.TeacherRepository;
import com.example.file.courseapp.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public ResponseEntity<?> createTeacher(TeacherDto teacher) {
        Teacher newTeacher = new Teacher();
        newTeacher.setEmail(teacher.getEmail());
        newTeacher.setAge(teacher.getAge());
        newTeacher.setFirstName(teacher.getFirstName());
        newTeacher.setLastName(teacher.getLastName());
        newTeacher.setAddress(teacher.getAddress());
        newTeacher.setPhoneNumber(teacher.getPhoneNumber());
        newTeacher.setCourseId(teacher.getCourseId());
        newTeacher.setDescription(teacher.getDescription());
        newTeacher.setCreatedAt(LocalDate.now());
        teacherRepository.save(newTeacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTeacher);

    }

    @Override
    public ResponseEntity<?> findTeacherById(Long id) {
        Teacher teacher = this.teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("teacher not found"));
        return ResponseEntity.status(HttpStatus.OK).body(teacher);

    }

    @Override
    public ResponseEntity<?> updateTeacher(TeacherDto teacher, Long id) {
        Teacher tech = this.teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("teacher not found"));
        tech.setEmail(teacher.getEmail());
        tech.setAge(teacher.getAge());
        tech.setFirstName(teacher.getFirstName());
        tech.setLastName(teacher.getLastName());
        tech.setAddress(teacher.getAddress());
        tech.setDescription(teacher.getDescription());
        tech.setUpdatedAt(LocalDate.now());
        teacherRepository.save(tech);
        return ResponseEntity.status(HttpStatus.OK).body(tech);

    }

    @Override
    public ResponseEntity<?> deleteTeacher(Long id) {
        Teacher tech = this.teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("teacher not found"));
        teacherRepository.delete(tech);
        return ResponseEntity.status(HttpStatus.OK).body(tech);
    }


    @Override
    public ResponseEntity<?> findAllTeachers() {
        List<Teacher> all = this.teacherRepository.findAll();
        if (!all.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(all);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("list is empty");
    }

    @Override
    public ResponseEntity<?> getAllPage(Pageable pageable) {
        List<Teacher> teachers = this.teacherRepository.findAll();
        if (!teachers.isEmpty()) {
            List<TeacherDto> dtos = teachers.stream()
                    .map(teacher -> new TeacherDto(
                            teacher.getId(),
                            teacher.getCourseId(),
                            teacher.getFirstName(),
                            teacher.getLastName(),
                            teacher.getEmail(),
                            teacher.getPhoneNumber(),
                            teacher.getAddress(),
                            teacher.getAge(),
                            teacher.getDescription(),
                            teacher.getCreatedAt(),
                            teacher.getUpdatedAt()
                    )).toList();

            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("list is empty");
    }
}
