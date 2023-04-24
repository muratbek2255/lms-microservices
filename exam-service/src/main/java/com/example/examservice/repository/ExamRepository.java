package com.example.examservice.repository;

import com.example.examservice.entity.Exam;
import com.example.examservice.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

    @Query(value = "SELECT * from exams WHERE id=?1", nativeQuery = true)
    Exam getById(int id);

    Exam findByCourseId(int courseId);

    Exam findByCategory_Name(String categoryName);

    @Query(value = "SELECT * FROM exams WHERE password=:password", nativeQuery = true)
    List<Exam> getByPassword(@Param("password") String password);
}
