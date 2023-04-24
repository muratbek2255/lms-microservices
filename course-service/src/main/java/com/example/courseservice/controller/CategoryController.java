package com.example.courseservice.controller;


import com.example.courseservice.dto.CategoryRequest;
import com.example.courseservice.dto.CategoryResponse;
import com.example.courseservice.service.CategoryServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public ResponseEntity<CategoryResponse> getAllCategory() {

        return ResponseEntity.status(200).body(categoryService.findAll());
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(CategoryRequest categoryRequest) {

        return ResponseEntity.status(201).body(categoryService.addCategory(categoryRequest));
    }
}
