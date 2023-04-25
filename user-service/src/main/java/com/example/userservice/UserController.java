package com.example.userservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @PostMapping("/addExamQuestion")
    public ResponseEntity<String> addUserToExamQuestion(@RequestParam("password") String password,
                                                        @RequestParam("userId") Integer userId,
                                                        @RequestBody StudentAnswerRequest studentAnswerRequest) {

        return ResponseEntity.status(201).body(userService.addUserIdToExam(password, userId, studentAnswerRequest));
    }

}
