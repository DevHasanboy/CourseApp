package com.example.file.courseapp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime publishDate;
    private LocalDateTime updatedAt;
}
