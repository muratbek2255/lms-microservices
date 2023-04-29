package com.example.userservice;

import com.example.userservice.client.CourseClient;
import com.example.userservice.client.ExamQuestionClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseClient client;

    @Mock
    private ExamQuestionClient examQuestionClient;

    @Mock
    private AddOwnerRequest addOwnerRequest;

    @Mock
    private AddUserToParticipationRequest addUserToParticipationRequest;

    @Mock
    private StudentAnswerRequest studentAnswerRequest;

    @InjectMocks
    private UserServiceImpl userService;


    @BeforeEach
    public void setUp() {

        addOwnerRequest = new AddOwnerRequest();
        addUserToParticipationRequest = new AddUserToParticipationRequest();
        studentAnswerRequest = new StudentAnswerRequest();
    }

    @Test
    public void testAddUserToCourse() {

        int id = 1;

        addOwnerRequest.setOwnerId(id);

        String result = userService.addUserToCourse(addOwnerRequest);

        assertEquals("add User To Course Entity", result);
    }
}