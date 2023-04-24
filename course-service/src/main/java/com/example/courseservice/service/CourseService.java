package com.example.courseservice.service;

import com.example.courseservice.dto.*;

public interface CourseService {

    public String addOwnerToCourse(AddOwnerRequest addOwnerRequest);

    public CourseResponse addCourse(int id, CourseRequest courseRequest);

    public CourseResponse getByIdCourse(int id);

    public CourseResponse getByOwnerId(int ownerId);

    public CourseResponse getByCategoryCourseName(String categoryName);

    public String addCourseIdToPayment(PaymentCheckRequest paymentCheckRequest, int courseId);

    public String updateIsPaymentField(int id);
}
