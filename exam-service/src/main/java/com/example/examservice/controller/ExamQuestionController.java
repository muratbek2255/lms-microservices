package com.example.examservice.controller;


import com.example.examservice.dto.StudentAnswerRequest;
import com.example.examservice.service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/exam_question")
public class ExamQuestionController {

    private final ExamQuestionService examQuestionService;

    @Autowired
    public ExamQuestionController(ExamQuestionService examQuestionService) {
        this.examQuestionService = examQuestionService;
    }

    @PostMapping("/takeExam")
    public ResponseEntity<String> takeExam(@Param("password") String password, @RequestBody StudentAnswerRequest studentAnswerRequest) {

        return ResponseEntity.status(201).body(examQuestionService.takeExam(password, studentAnswerRequest));
    }
}
