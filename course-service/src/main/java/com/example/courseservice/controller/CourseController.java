package com.example.courseservice.controller;


import com.example.courseservice.dto.*;
import com.example.courseservice.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseServiceImpl courseService;

    @Autowired
    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/addOwnerToCourse")
    public ResponseEntity<String> addOwnerToCourse(@RequestBody AddOwnerRequest addOwnerRequest) {

        return ResponseEntity.status(201).body(courseService.addOwnerToCourse(addOwnerRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> addCourse(@PathVariable int id,
                                                    @RequestBody CourseRequest courseRequest) {

        return ResponseEntity.status(201).body(courseService.addCourse(id, courseRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getById(@PathVariable int id) {

        return ResponseEntity.status(200).body(courseService.getByIdCourse(id));
    }

    @GetMapping("/byOwnerId/{ownerId}")
    public ResponseEntity<CourseResponse> getByOwnerId(@PathVariable int ownerId) {

        return ResponseEntity.status(200).body(courseService.getByOwnerId(ownerId));
    }

    @GetMapping("/byCategoryCourseName")
    public ResponseEntity<CourseResponse> getByCategoryName(@Param("categoryName") String categoryName) {

        return ResponseEntity.status(200).body(courseService.getByCategoryCourseName(categoryName));
    }

    @PostMapping("/addTransaction/{id}")
    public ResponseEntity<String> addCourseIdToPayment(@RequestBody PaymentCheckRequest paymentCheckRequest,
                                                       @PathVariable int id) {

        return ResponseEntity.status(201).body(courseService.addCourseIdToPayment(paymentCheckRequest, id));
    }

    @PutMapping("/UpdateIsPayment/{id}")
    public ResponseEntity<String> updateIsPaymentField(@PathVariable int id) {

        return ResponseEntity.status(201).body(courseService.updateIsPaymentField(id));
    }

    @PostMapping("/addCourseToExam")
    public ResponseEntity<String> addCourseIdToExam(@RequestBody AddCourseIdToExamRequest addCourseIdToExamRequest) {

        return ResponseEntity.status(201).body(courseService.addCourseIdToExam(addCourseIdToExamRequest));
    }

    @PostMapping("/addCourseToQuestion")
    public ResponseEntity<String> addCourseIdToQuestion(@RequestBody AddCourseIdToQuestionRequest addCourseIdToQuestionRequest) {

        return ResponseEntity.status(201).body(courseService.addCourseIdToQuestion(addCourseIdToQuestionRequest));
    }
}
