package com.example.userservice;


import com.example.userservice.client.CourseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final CourseClient client;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CourseClient client) {
        this.userRepository = userRepository;
        this.client = client;
    }


    @Override
    public String addUserToCourse(AddOwnerRequest addOwnerRequest) {

        User user = userRepository.getById(addOwnerRequest.getOwnerId());

        addOwnerRequest.setOwnerId(user.getId());

        client.addOwnerToCourse(addOwnerRequest);

        return "add User To Course Entity";
    }

    @Override
    public String addUserToParticipantEntity(AddUserToParticipationRequest addUserToParticipationRequest) {

        User user = userRepository.getById(addUserToParticipationRequest.getUserId());

        addUserToParticipationRequest.setUserId(user.getId());

        client.addUserToParticipant(addUserToParticipationRequest);

        return "add User To Participant";
    }

}
