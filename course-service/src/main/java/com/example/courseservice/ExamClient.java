package com.example.courseservice;


import com.example.courseservice.dto.AddCourseIdToExamRequest;
import com.example.courseservice.dto.AddCourseIdToQuestionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "exam-service", url = "http://localhost:8089")
public interface ExamClient {

    @PostMapping("/exam/addCourseId")
    public ResponseEntity<String> addCourseIdToExam(@RequestBody AddCourseIdToExamRequest addCourseIdToExamRequest);

    @PostMapping("/question/addCourseId")
    public ResponseEntity<String> addCourseIdToQuestion(@RequestBody AddCourseIdToQuestionRequest addCourseIdToQuestionRequest);
}
