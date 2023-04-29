package com.example.examservice.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import com.example.examservice.dto.AddCourseIdToQuestionRequest;
import com.example.examservice.dto.QuestionRequest;
import com.example.examservice.service.QuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class QuestionControllerTest {

    @Mock
    private QuestionServiceImpl questionService;

    @InjectMocks
    private QuestionController questionController;

    private AddCourseIdToQuestionRequest addCourseIdToQuestionRequest;
    private QuestionRequest questionRequest;

    @BeforeEach
    public void setUp() {
        addCourseIdToQuestionRequest = new AddCourseIdToQuestionRequest();
        questionRequest = new QuestionRequest();
    }

    @Test
    public void testAddCourseIdToQuestion() {
        // Arrange
        when(questionService.addCourseIdToQuestion(addCourseIdToQuestionRequest)).thenReturn("success");

        // Act
        ResponseEntity<String> response = questionController.addCourseIdToQuestion(addCourseIdToQuestionRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("success", response.getBody());
    }

    @Test
    public void testCreateQuestion() {
        // Arrange
        int id = 1;
        when(questionService.createQuestion(questionRequest, id)).thenReturn("success");

        // Act
        ResponseEntity<String> response = questionController.createQuestion(questionRequest, id);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("success", response.getBody());
    }
}
