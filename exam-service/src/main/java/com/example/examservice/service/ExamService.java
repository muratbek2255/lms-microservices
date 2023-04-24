package com.example.examservice.service;

import com.example.examservice.dto.AddCourseIdToExamRequest;
import com.example.examservice.dto.ExamRequest;

public interface ExamService {

    public String addCourseIdToExam(AddCourseIdToExamRequest addCourseIdToExamRequest);

    public String createExam(ExamRequest examRequest, int id);
}
