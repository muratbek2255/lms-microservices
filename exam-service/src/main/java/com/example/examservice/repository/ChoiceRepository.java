package com.example.examservice.repository;


import com.example.examservice.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChoiceRepository extends JpaRepository<Choice, Integer> {

    @Query("SELECT c.id, c.choiceText, c.correct FROM Choice c WHERE c.choiceText=:choise_text")
    Choice findByChoiceText(@Param("choise_text") String choise_text);
}
