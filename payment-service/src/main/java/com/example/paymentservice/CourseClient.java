package com.example.paymentservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "course-service", url = "http://localhost:8082")
public interface CourseClient {

    @PutMapping("/courses/UpdateIsPayment/{id}")
    public ResponseEntity<String> updateIsPaymentField(@PathVariable int id);
}
