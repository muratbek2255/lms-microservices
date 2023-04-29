package com.example.courseservice.service;

import com.example.courseservice.PaymentClient;
import com.example.courseservice.ExamClient;
import com.example.courseservice.dto.*;
import com.example.courseservice.entity.Category;
import com.example.courseservice.entity.Course;
import com.example.courseservice.repository.CategoryRepository;
import com.example.courseservice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CategoryRepository categoryRepository;

    private final PaymentClient paymentClient;

    private final ExamClient examClient;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, CategoryRepository categoryRepository, PaymentClient paymentClient, ExamClient examClient) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
        this.paymentClient = paymentClient;
        this.examClient = examClient;
    }

    @Override
    public String addOwnerToCourse(AddOwnerRequest addOwnerRequest) {

        Course course = new Course();

        course.setOwnerId(addOwnerRequest.getOwnerId());
        course.setCreatedAt(Timestamp.from(Instant.now()));

        courseRepository.save(course);

        return "Add owner To Course";
    }

    @Override
    public CourseResponse addCourse(int id, CourseRequest courseRequest) {

        Course course = courseRepository.getById(id);

        Category category = categoryRepository.getById(courseRequest.getCategoryRequest().getId());

        course.setName(courseRequest.getName());
        course.setDescription(courseRequest.getDescription());
        course.setUpdatedAt(Timestamp.from(Instant.now()));
        course.setCategory(category);
        course.setIsPayment(Boolean.FALSE);

        courseRepository.save(course);

        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setOwnerId(course.getOwnerId());
        courseResponse.setName(course.getName());
        courseResponse.setDescription(course.getDescription());

        return courseResponse;
    }

    @Override
    public CourseResponse getByIdCourse(int id) {

        Course course = courseRepository.getById(id);

        CourseResponse courseResponse = new CourseResponse();

        if (course.getIsPayment()) {

            courseResponse.setId(course.getId());
            courseResponse.setName(course.getName());
            courseResponse.setDescription(course.getDescription());
            courseResponse.setOwnerId(course.getOwnerId());

        }

        return courseResponse;
    }

    @Override
    public CourseResponse getByOwnerId(int ownerId) {

        Course course = courseRepository.findByOwnerId(ownerId);

        CourseResponse courseResponse = new CourseResponse();

        courseResponse.setId(course.getId());
        courseResponse.setName(course.getName());
        courseResponse.setDescription(course.getDescription());
        courseResponse.setOwnerId(course.getOwnerId());

        return courseResponse;
    }

    @Override
    public CourseResponse getByCategoryCourseName(String categoryName) {

        List<Course> course = courseRepository.findByCategory_Name(categoryName);

        CourseResponse courseResponse = new CourseResponse();

        courseResponse.setId(course.get(0).getId());
        courseResponse.setName(course.get(0).getName());
        courseResponse.setDescription(course.get(0).getDescription());
        courseResponse.setOwnerId(course.get(0).getOwnerId());

        return courseResponse;
    }

    @Override
    public String addCourseIdToPayment(PaymentCheckRequest paymentCheckRequest, int courseId) {

        Course course = courseRepository.getById(courseId);

        paymentClient.checkPayment(paymentCheckRequest, course.getId());

        return "Add courseId to Payment";
    }

    @Override
    public String updateIsPaymentField(int id) {

        Course course = courseRepository.getById(id);

        course.setIsPayment(Boolean.TRUE);

        courseRepository.save(course);

        return "Course field isPaymentUpdate";
    }

    public String addCourseIdToExam(AddCourseIdToExamRequest addCourseIdToExamRequest) {

        Course course = courseRepository.getById(addCourseIdToExamRequest.getCourseId());

        addCourseIdToExamRequest.setCourseId(course.getId());

        examClient.addCourseIdToExam(addCourseIdToExamRequest);

        return "add Course Id To Exam";
    }

    public String addCourseIdToQuestion(AddCourseIdToQuestionRequest addCourseIdToQuestionRequest) {

        Course course = courseRepository.getById(addCourseIdToQuestionRequest.getCourseId());

        addCourseIdToQuestionRequest.setCourseId(course.getId());

        examClient.addCourseIdToQuestion(addCourseIdToQuestionRequest);

        return "add Course Id To Question";
    }
}
