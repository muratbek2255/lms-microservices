package com.example.examservice.repository;


import com.example.examservice.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChoiceRepository extends JpaRepository<Choice, Integer> {

    @Query(value = "SELECT id, choise_text, correct, question_id FROM choices WHERE choise_text=:choise_text", nativeQuery = true)
    Choice findByChoiceText(@Param("choise_text") String choise_text);
}
