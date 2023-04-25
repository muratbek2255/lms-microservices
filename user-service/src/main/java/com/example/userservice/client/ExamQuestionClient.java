package com.example.userservice.client;


import com.example.userservice.StudentAnswerRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "exam-service", url = "http://localhost:8085")
public interface ExamQuestionClient {

    @PostMapping("/exam_question/takeExam")
    public ResponseEntity<String> takeExam(@RequestParam("password") String password, @RequestParam("userId") Integer userId,
                                           @RequestBody StudentAnswerRequest studentAnswerRequest);
}
