package com.example.courseservice.repository;

import com.example.courseservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query(value = "SELECT id, owner, name, description, category_id, created_at, updated_at, is_payment from courses WHERE id=?1", nativeQuery = true)
    Course getById(int id);

    Course findByOwnerId(@Param("ownerId") int ownerId);

    List<Course> findByCategory_Name(@Param("categoryName") String categoryName);
}
