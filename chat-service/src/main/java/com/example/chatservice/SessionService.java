package com.example.chatservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.List;


@Service
public class SessionService {

    private final ChatService chatService;
    private final FindByIndexNameSessionRepository<? extends Session> sessionRepository;
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public SessionService(ChatService chatService, FindByIndexNameSessionRepository<? extends Session> sessionRepository,
                          KafkaTemplate<String, String> kafkaTemplate) {
        this.chatService = chatService;
        this.sessionRepository = sessionRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<UserResponse> getContacts(Integer userId) {
        Instant now = Instant.now();
        List<User> contacts = chatService.getContacts(Math.toIntExact(userId));

        return contacts.stream()
                .map(user -> {
                    Instant lastAccessedTime = getLastAccessedTime(
                            sessionRepository.findByPrincipalName(user.getPhoneNumber()).values()
                    );

                    boolean online = Duration.between(lastAccessedTime, now).getSeconds() < 60;

                    kafkaTemplate.send("lms", "User with id: " + user.getId() + "in online");

                    return new UserResponse(user, online);
                }).toList();
    }

    private Instant getLastAccessedTime(Collection<? extends Session> sessions) {
        Instant lastAccessedTime = Instant.MIN;

        if (CollectionUtils.isEmpty(sessions))
            return lastAccessedTime;

        for (Session ses : sessions) {
            if (lastAccessedTime.isBefore(ses.getLastAccessedTime()))
                lastAccessedTime = ses.getLastAccessedTime();
        }

        return lastAccessedTime;
    }
}
