package com.example.courseservice.service;


import com.example.courseservice.dto.AddUserToParticipationRequest;
import com.example.courseservice.dto.ParticipantRequest;
import com.example.courseservice.dto.ParticipantResponse;

public interface ParticipationService {

    public ParticipantResponse addParticipant(ParticipantRequest participantRequest, int id);

    public String addUserToParticipant(AddUserToParticipationRequest addUserToParticipationRequest);

    public ParticipantResponse deleteParticipant();
}
