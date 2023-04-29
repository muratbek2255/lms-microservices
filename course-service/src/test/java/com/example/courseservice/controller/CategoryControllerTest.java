package com.example.courseservice.controller;

import com.example.courseservice.dto.CategoryRequest;
import com.example.courseservice.dto.CategoryResponse;
import com.example.courseservice.service.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {


    @Mock
    private CategoryServiceImpl categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryRequest categoryRequest;


    @BeforeEach
    public void setUp() {

        categoryRequest = new CategoryRequest();
    }

    @Test
    public void addCategory() {

        CategoryResponse categoryResponse = new CategoryResponse();

        Mockito.when(categoryService.addCategory(categoryRequest)).thenReturn(categoryResponse);

        ResponseEntity<CategoryResponse> response = categoryController.addCategory(categoryRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}