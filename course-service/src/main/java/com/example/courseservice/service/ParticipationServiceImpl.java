package com.example.courseservice.service;

import com.example.courseservice.dto.AddUserToParticipationRequest;
import com.example.courseservice.dto.ParticipantRequest;
import com.example.courseservice.dto.ParticipantResponse;
import com.example.courseservice.entity.Course;
import com.example.courseservice.entity.Participation;
import com.example.courseservice.repository.CourseRepository;
import com.example.courseservice.repository.ParticipationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ParticipationServiceImpl implements ParticipationService {

    private final ParticipationRepository participationRepository;

    private final CourseRepository courseRepository;

    @Autowired
    public ParticipationServiceImpl(ParticipationRepository participationRepository, CourseRepository courseRepository) {
        this.participationRepository = participationRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public ParticipantResponse addParticipant(ParticipantRequest participantRequest, int id) {

        Participation participation = participationRepository.getById(id);

        Course course = courseRepository.getById(participantRequest.getCourseRequest().getId());

        participation.setCourse(course);

        participationRepository.save(participation);

        ParticipantResponse participantResponse = new ParticipantResponse();

        participantResponse.setId(participation.getId());
        participantResponse.setCourse(participation.getCourse());

        return participantResponse;
    }

    @Override
    public String addUserToParticipant(AddUserToParticipationRequest addUserToParticipationRequest) {

        Participation participation = new Participation();

        participation.setUserId(addUserToParticipationRequest.getUserId());

        participationRepository.save(participation);

        return "Add user For Participant";
    }

    @Override
    public ParticipantResponse deleteParticipant() {
        return null;
    }
}
