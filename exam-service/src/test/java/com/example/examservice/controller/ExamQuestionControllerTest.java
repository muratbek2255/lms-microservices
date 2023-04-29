package com.example.examservice.controller;

import com.example.examservice.dto.StudentAnswerRequest;
import com.example.examservice.service.ExamQuestionService;
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
class ExamQuestionControllerTest {

    @Mock
    private ExamQuestionService examQuestionService;

    @InjectMocks
    private ExamQuestionController examQuestionController;

    @Mock
    private StudentAnswerRequest studentAnswerRequest;

    @BeforeEach
    public void setUp() {

        studentAnswerRequest = new StudentAnswerRequest();
    }

    @Test
    public void testTakeExam() {

        String password = "1234";
        int userId = 1;

        Mockito.when(examQuestionService.takeExam(password, userId, studentAnswerRequest)).thenReturn("success");

        ResponseEntity<String> response = examQuestionController.takeExam(password, userId, studentAnswerRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("success", response.getBody());
    }
}