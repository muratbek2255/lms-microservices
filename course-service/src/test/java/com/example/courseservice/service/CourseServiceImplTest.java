package com.example.courseservice.service;

import com.example.courseservice.ExamClient;
import com.example.courseservice.PaymentClient;
import com.example.courseservice.dto.AddOwnerRequest;
import com.example.courseservice.dto.CategoryRequest;
import com.example.courseservice.dto.CourseRequest;
import com.example.courseservice.dto.CourseResponse;
import com.example.courseservice.entity.Category;
import com.example.courseservice.entity.Course;
import com.example.courseservice.repository.CategoryRepository;
import com.example.courseservice.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private PaymentClient paymentClient;

    @Mock
    private ExamClient examClient;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void addOwnerToCourseTest() {

        AddOwnerRequest addOwnerRequest = new AddOwnerRequest();

        addOwnerRequest.setOwnerId(1);

        Course course = new Course();

        course.setOwnerId(addOwnerRequest.getOwnerId());
        course.setCreatedAt(Timestamp.from(Instant.now()));

        when(courseRepository.save(any())).thenReturn(course);

        String response = "Add owner To Course";

        String realResponse = courseService.addOwnerToCourse(addOwnerRequest);

        assertEquals(response, realResponse);
    }

    @Test
    void addCourse() {

        CourseRequest courseRequest = new CourseRequest();

        CategoryRequest categoryRequest = new CategoryRequest();

        categoryRequest.setId(1);
        categoryRequest.setName("SOO");

        courseRequest.setId(1);
        courseRequest.setName("My Money");
        courseRequest.setDescription("Is be famous course in the world");
        courseRequest.setCategoryRequest(categoryRequest);

        Course course = new Course();
        when(courseRepository.getById(courseRequest.getId())).thenReturn(course);

        Category category = categoryRepository.getById(courseRequest.getCategoryRequest().getId());

        course.setName(courseRequest.getName());
        course.setDescription(courseRequest.getDescription());
        course.setUpdatedAt(Timestamp.from(Instant.now()));
        course.setCategory(category);
        course.setIsPayment(Boolean.FALSE);

        when(courseRepository.save(any())).thenReturn(course);

        CourseResponse courseResponse = new CourseResponse();

        courseResponse.setId(course.getId());
        courseResponse.setOwnerId(course.getOwnerId());
        courseResponse.setName(course.getName());
        courseResponse.setDescription(course.getDescription());

        CourseResponse courseResponse1 = courseService.addCourse(1, courseRequest);

        assertEquals(courseResponse, courseResponse1);
    }
}