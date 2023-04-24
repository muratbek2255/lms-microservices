package com.example.courseservice.service;

import com.example.courseservice.dto.CategoryRequest;
import com.example.courseservice.dto.CategoryResponse;
import com.example.courseservice.entity.Category;
import com.example.courseservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse findAll() {

        List<Category> category = categoryRepository.findAll();

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setName(category.get(0).getName());

        return categoryResponse;
    }


    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {

        Category category = new Category();

        category.setName(categoryRequest.getName());

        categoryRepository.save(category);

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setName(categoryRequest.getName());

        return categoryResponse;
    }
}
