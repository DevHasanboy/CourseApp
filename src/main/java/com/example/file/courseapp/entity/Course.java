package com.example.file.courseapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cost;
    private String language;
    @OneToMany(mappedBy = "courseId", fetch = FetchType.EAGER)
    private List<Teacher> teacherList;
   private Integer contactId;
    private LocalDate createdAt;
    private LocalDate updateAt;
}
