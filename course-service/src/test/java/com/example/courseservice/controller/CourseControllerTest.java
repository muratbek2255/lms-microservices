package com.example.courseservice.controller;

import com.example.courseservice.dto.AddOwnerRequest;
import com.example.courseservice.dto.CourseRequest;
import com.example.courseservice.dto.PaymentCheckRequest;
import com.example.courseservice.service.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @Mock
    private CourseServiceImpl courseService;

    @Mock
    private AddOwnerRequest addOwnerRequest;

    @Mock
    private CourseRequest courseRequest;

    @Mock
    private PaymentCheckRequest paymentCheckRequest;

    @InjectMocks
    private CourseController courseController;


    @BeforeEach
    public void setUp() {
        courseController = new CourseController(courseService);
        addOwnerRequest = new AddOwnerRequest();
        courseRequest = new CourseRequest();
        paymentCheckRequest = new PaymentCheckRequest();
    }


    @Test
    public void testOwnerAddToCourse() {

        Mockito.when(courseService.addOwnerToCourse(addOwnerRequest)).thenReturn("success");

        ResponseEntity<String> response = courseController.addOwnerToCourse(addOwnerRequest);

        assertEquals("success", response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testAddCourseIdToPayment() {

        int id = 1;

        Mockito.when(courseService.addCourseIdToPayment(paymentCheckRequest, id)).thenReturn("success");

        ResponseEntity<String> response = courseController.addCourseIdToPayment(paymentCheckRequest, id);

        assertEquals("success", response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}