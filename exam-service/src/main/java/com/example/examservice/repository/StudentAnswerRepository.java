package com.example.examservice.repository;

import com.example.examservice.entity.Category;
import com.example.examservice.entity.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Integer> {

    @Query(value = "SELECT * from student_answers WHERE id=?1", nativeQuery = true)
    StudentAnswer getById(int id);

}
