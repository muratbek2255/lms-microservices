package com.example.userservice.client;


import com.example.userservice.AddOwnerRequest;
import com.example.userservice.AddUserToParticipationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "course-service", url = "http://localhost:8084")
public interface CourseClient {

    @PostMapping("/courses/addOwnerToCourse")
    public ResponseEntity<String> addOwnerToCourse(@RequestBody AddOwnerRequest addOwnerRequest);

    @PostMapping("/participant/addUser")
    public ResponseEntity<String> addUserToParticipant(@RequestBody AddUserToParticipationRequest addUserToParticipationRequest);
}
