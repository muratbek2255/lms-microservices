package com.example.examservice.controller;

import com.example.examservice.dto.AddCourseIdToExamRequest;
import com.example.examservice.dto.ExamRequest;
import com.example.examservice.service.ExamServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ExamControllerTest {


    @Mock
    private ExamServiceImpl examService;

    @InjectMocks
    private ExamController examController;

    @Mock
    private AddCourseIdToExamRequest addCourseIdToExamRequest;

    @Mock
    private ExamRequest examRequest;


    @BeforeEach
    public void setUp() {
        addCourseIdToExamRequest = new AddCourseIdToExamRequest();
        examRequest = new ExamRequest();
    }


    @Test
    public void testAddCourseIdToExam() {

//        when(examService.addCourseIdToExam(addCourseIdToExamRequest)).thenReturn("success");

        // Arrange
        Mockito.when(examService.addCourseIdToExam(addCourseIdToExamRequest)).thenReturn("success");

        ResponseEntity<String> response = examController.addCourseIdToExam(addCourseIdToExamRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("success", response.getBody());
    }

    @Test
    public void testCreateExam() {

        int id = 1;

        Mockito.when(examService.createExam(examRequest, id)).thenReturn("success");

        ResponseEntity<String> response = examController.createExam(examRequest, id);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("success", response.getBody());
    }
}