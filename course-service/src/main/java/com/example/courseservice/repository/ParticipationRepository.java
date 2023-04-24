package com.example.courseservice.repository;

import com.example.courseservice.entity.Course;
import com.example.courseservice.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParticipationRepository extends JpaRepository<Participation, Integer> {

    @Query(value = "SELECT id, user_id, course_id from participants WHERE id=?1", nativeQuery = true)
    Participation getById(int id);

}
