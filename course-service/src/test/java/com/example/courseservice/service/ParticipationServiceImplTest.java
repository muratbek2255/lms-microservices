package com.example.courseservice.service;

import com.example.courseservice.dto.CategoryRequest;
import com.example.courseservice.dto.CourseRequest;
import com.example.courseservice.dto.ParticipantRequest;
import com.example.courseservice.entity.Participation;
import com.example.courseservice.repository.CourseRepository;
import com.example.courseservice.repository.ParticipationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ParticipationServiceImplTest {


    @Mock
    private ParticipationRepository participationRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private ParticipationServiceImpl participationService;

    @Test
    void testPartcipiant() {

        CourseRequest courseRequest = new CourseRequest();

        CategoryRequest categoryRequest = new CategoryRequest();

        categoryRequest.setId(1);
        categoryRequest.setName("SOO");

        courseRequest.setId(1);
        courseRequest.setName("My Money");
        courseRequest.setDescription("Is be famous course in the world");
        courseRequest.setCategoryRequest(categoryRequest);

        ParticipantRequest participantRequest = new ParticipantRequest();

        participantRequest.setCourseRequest(courseRequest);

        Participation participation = new Participation();

        Mockito.when(participationRepository.getById(1)).thenReturn(participation);
    }
}