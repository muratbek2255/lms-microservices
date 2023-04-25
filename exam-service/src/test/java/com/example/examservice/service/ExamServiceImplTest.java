package com.example.examservice.service;

import com.example.examservice.dto.AddCourseIdToExamRequest;
import com.example.examservice.dto.ExamRequest;
import com.example.examservice.entity.Category;
import com.example.examservice.entity.Exam;
import com.example.examservice.repository.CategoryRepository;
import com.example.examservice.repository.ExamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ExamServiceImplTest {

    @Mock
    private ExamRepository examRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ExamServiceImpl examService;


    @Test
    public void addCourseIdToExam() {

        AddCourseIdToExamRequest addCourseIdToExamRequest = new AddCourseIdToExamRequest();

        addCourseIdToExamRequest.setCourseId(1);

        Exam exam = new Exam();

        exam.setCourseId(addCourseIdToExamRequest.getCourseId());

        when(examRepository.save(any())).thenReturn(exam);

        String exams = "Add Exam";

        String examResponse = examService.addCourseIdToExam(addCourseIdToExamRequest);

        assertEquals(exams, examResponse);
    }

    @Test
    public void createExam() {

        ExamRequest examRequest = new ExamRequest();

        examRequest.setName("JavaExam");
        examRequest.setName("1234567890");
        examRequest.setTimeLimit(new Time(System.currentTimeMillis()));
        examRequest.setActiveFrom(LocalDateTime.now());
        examRequest.setActiveTo(LocalDateTime.now());
        examRequest.setQuestionCount(10);

        Exam exam = new Exam();

        Category category = categoryRepository.getById(5);

        exam.setName(examRequest.getName());
        exam.setPassword(examRequest.getPassword());
        exam.setCreated_at(Timestamp.from(Instant.now()));
        exam.setTimeLimit(examRequest.getTimeLimit());
        exam.setActiveFrom(examRequest.getActiveFrom());
        exam.setActiveTo(examRequest.getActiveTo());
        exam.setCategory(category);

        when(examRepository.save(any())).thenReturn(exam);

        String exams = "Create Exam";

        String examResponse = examService.createExam(examRequest, 5);

        assertEquals(exams, examResponse);
    }
}