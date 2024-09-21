package com.example.file.courseapp.dto;

import com.example.file.courseapp.entity.Course;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
    private Long id;
    private String title;
    private String content;
    private List<Course> courseList;
    private LocalDateTime publishDate;
    private LocalDateTime updatedAt;
}
