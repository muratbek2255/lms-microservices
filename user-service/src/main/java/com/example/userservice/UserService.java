package com.example.userservice;


public interface UserService {

    public String addUserToCourse(AddOwnerRequest addOwnerRequest);

    public String addUserToParticipantEntity(AddUserToParticipationRequest addUserToParticipationRequest);
}
