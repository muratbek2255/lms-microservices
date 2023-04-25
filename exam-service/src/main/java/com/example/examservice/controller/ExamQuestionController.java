package com.example.examservice.controller;


import com.example.examservice.dto.StudentAnswerRequest;
import com.example.examservice.service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/exam_question")
public class ExamQuestionController {

    private final ExamQuestionService examQuestionService;

    @Autowired
    public ExamQuestionController(ExamQuestionService examQuestionService) {
        this.examQuestionService = examQuestionService;
    }

    @PostMapping("/takeExam")
    public ResponseEntity<String> takeExam(@RequestParam("password") String password, @RequestParam("userId") Integer userId,
                                           @RequestBody StudentAnswerRequest studentAnswerRequest) {

        return ResponseEntity.status(201).body(examQuestionService.takeExam(password, userId, studentAnswerRequest));
    }
}
