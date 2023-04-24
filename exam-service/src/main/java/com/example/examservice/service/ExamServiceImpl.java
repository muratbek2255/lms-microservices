package com.example.examservice.service;

import com.example.examservice.dto.AddCourseIdToExamRequest;
import com.example.examservice.dto.ExamRequest;
import com.example.examservice.entity.Category;
import com.example.examservice.entity.Exam;
import com.example.examservice.repository.CategoryRepository;
import com.example.examservice.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;


@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ExamServiceImpl(ExamRepository examRepository, CategoryRepository categoryRepository) {
        this.examRepository = examRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public String addCourseIdToExam(AddCourseIdToExamRequest addCourseIdToExamRequest) {

        Exam exam = new Exam();

        exam.setCourseId(addCourseIdToExamRequest.getCourseId());

        examRepository.save(exam);

        return "Add Exam";
    }

    @Override
    public String createExam(ExamRequest examRequest, int id) {

        Exam exam = examRepository.getById(id);

        Category category = categoryRepository.getById(examRequest.getCategoryRequest().getId());

        exam.setName(examRequest.getName());
        exam.setPassword(examRequest.getPassword());
        exam.setCreated_at(Timestamp.from(Instant.now()));
        exam.setTimeLimit(examRequest.getTimeLimit());
        exam.setActiveFrom(examRequest.getActiveFrom());
        exam.setActiveTo(examRequest.getActiveTo());
        exam.setCategory(category);

        examRepository.save(exam);

        return "Create Exam";
    }

}
