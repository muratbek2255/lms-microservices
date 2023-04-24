package com.example.examservice.service;


import com.example.examservice.dto.AddCourseIdToQuestionRequest;
import com.example.examservice.dto.QuestionRequest;

public interface QuestionService {

    public String addCourseIdToQuestion(AddCourseIdToQuestionRequest addCourseIdToQuestionRequest);

    public String createQuestion(QuestionRequest questionRequest, int id);
}
