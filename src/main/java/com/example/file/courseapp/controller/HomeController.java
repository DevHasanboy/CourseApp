package com.example.file.courseapp.controller;

import com.example.file.courseapp.service.CourseService;
import com.example.file.courseapp.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class HomeController {
    private final TeacherService teacherService;
    private final CourseService courseService;


    @Operation(summary = "to get all the home", description = "home get all", tags = "GET-ALL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "home get all successfully "),
            @ApiResponse(responseCode = "404", description = " home not found")
    })
    @GetMapping("/all_course")
    public ResponseEntity<?> getAllCourses() {
        ResponseEntity response = courseService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "to get all the course", description = "course get all", tags = "GET-ALL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "course get all successfully "),
            @ApiResponse(responseCode = "404", description = " course not found")
    })

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/all_teacher")
    public ResponseEntity<?> getAllTeachers() {
        ResponseEntity response = this.teacherService.findAllTeachers();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
