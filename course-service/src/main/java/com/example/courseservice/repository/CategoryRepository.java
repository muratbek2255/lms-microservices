package com.example.courseservice.repository;

import com.example.courseservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * from categories WHERE id=?1", nativeQuery = true)
    Category getById(int id);
}
