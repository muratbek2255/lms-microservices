package com.example.courseservice.controller;


import com.example.courseservice.dto.AddUserToParticipationRequest;
import com.example.courseservice.dto.ParticipantRequest;
import com.example.courseservice.dto.ParticipantResponse;
import com.example.courseservice.service.ParticipationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/participant")
public class ParticipantController {

    private final ParticipationServiceImpl participationService;

    @Autowired
    public ParticipantController(ParticipationServiceImpl participationService) {
        this.participationService = participationService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUserToParticipant(@RequestBody AddUserToParticipationRequest addUserToParticipationRequest) {

        return ResponseEntity.status(201).body(participationService.addUserToParticipant(addUserToParticipationRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantResponse> addParticipant(ParticipantRequest participantRequest, int id) {

        return ResponseEntity.status(201).body(participationService.addParticipant(participantRequest, id));
    }

}
