package com.example.courseservice.controller;

import com.example.courseservice.service.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @Mock
    private CourseServiceImpl courseService;

    @InjectMocks
    private CourseController courseController;


    @BeforeEach
    public void setUp() {
        courseController = new CourseController(courseService);
    }
}