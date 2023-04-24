package com.example.examservice.controller;


import com.example.examservice.dto.AddCourseIdToQuestionRequest;
import com.example.examservice.dto.QuestionRequest;
import com.example.examservice.service.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionServiceImpl questionService;

    @Autowired
    public QuestionController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/addCourseId")
    public ResponseEntity<String> addCourseIdToQuestion(@RequestBody AddCourseIdToQuestionRequest addCourseIdToQuestionRequest) {

        return ResponseEntity.status(201).body(questionService.addCourseIdToQuestion(addCourseIdToQuestionRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> createQuestion(@RequestBody QuestionRequest questionRequest, @PathVariable int id) {

        return ResponseEntity.status(201).body(questionService.createQuestion(questionRequest, id));
    }
}
