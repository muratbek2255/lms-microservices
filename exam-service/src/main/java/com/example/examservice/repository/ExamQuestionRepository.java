package com.example.examservice.repository;

import com.example.examservice.entity.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, Integer> {

    List<ExamQuestion> findByExam_Id(int examId);

    @Query("select eq FROM ExamQuestion eq join eq.exam e WHERE e.password=:password")
    List<ExamQuestion> findByExamPassword(@Param("password") String password);
}
