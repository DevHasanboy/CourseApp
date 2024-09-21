package com.example.file.courseapp.dto;

import com.example.file.courseapp.entity.Teacher;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Long id;
    private String name;
    private String cost;
    private String language;
    private Integer contactId;
    private List<Teacher> teacherList;
    private LocalDate createdAt;
    private LocalDate updateAt;

}
