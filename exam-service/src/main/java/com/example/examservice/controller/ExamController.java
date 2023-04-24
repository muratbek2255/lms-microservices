package com.example.examservice.controller;


import com.example.examservice.dto.AddCourseIdToExamRequest;
import com.example.examservice.dto.ExamRequest;
import com.example.examservice.service.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExamServiceImpl examService;

    @Autowired
    public ExamController(ExamServiceImpl examService) {
        this.examService = examService;
    }

    @PostMapping("/addCourseId")
    public ResponseEntity<String> addCourseIdToExam(@RequestBody AddCourseIdToExamRequest addCourseIdToExamRequest) {

        return ResponseEntity.status(201).body(examService.addCourseIdToExam(addCourseIdToExamRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> createExam(@RequestBody ExamRequest examRequest, @PathVariable int id) {

        return ResponseEntity.status(201).body(examService.createExam(examRequest, id));
    }
}
