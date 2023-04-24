package com.example.courseservice.service;


import com.example.courseservice.dto.CategoryRequest;
import com.example.courseservice.dto.CategoryResponse;


public interface CategoryService {

    public CategoryResponse findAll();

    public CategoryResponse addCategory(CategoryRequest categoryRequest);
}
