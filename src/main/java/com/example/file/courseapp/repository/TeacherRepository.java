package com.example.file.courseapp.repository;

import com.example.file.courseapp.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query(value = "select * from teachers where course_id=:courseId", nativeQuery = true)
    List<Teacher> findByCourseId( @Param("courseId") Long courseId);
}
