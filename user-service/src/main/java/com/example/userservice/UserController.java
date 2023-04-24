package com.example.userservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/addCourse")
    public ResponseEntity<String> addUserToCourse(@RequestBody AddOwnerRequest addOwnerRequest) {

        return ResponseEntity.status(201).body(userService.addUserToCourse(addOwnerRequest));
    }

    @PostMapping("/addParticipant")
    public ResponseEntity<String> addUserToParticipant(@RequestBody AddUserToParticipationRequest addUserToParticipationRequest) {

        return ResponseEntity.status(201).body(userService.addUserToParticipantEntity(addUserToParticipationRequest));
    }

}
