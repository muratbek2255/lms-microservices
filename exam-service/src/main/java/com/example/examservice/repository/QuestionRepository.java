package com.example.examservice.repository;


import com.example.examservice.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query(value = "SELECT * from questions WHERE id=?1", nativeQuery = true)
    Question getById(int id);
}
