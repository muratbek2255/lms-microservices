package com.example.chatservice;

import com.example.chatservice.Chat;
import com.example.chatservice.ChatRepository;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

import static com.example.chatservice.Utils.chatId;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final ChatRepository chatMessageRepo;

    private final UserRepository userRepository;

    public List<Chat> getLastMessages(String chatId) {
        return chatMessageRepo.findAllByChatIdOrderByCreatedAtDesc(chatId, PageRequest.of(0, 10)).stream()
                .sorted(Comparator.comparing(Chat::getCreatedAt))
                .toList();
    }

    public Chat saveMessage(Integer authorId, Long contactId) {
        String chatId = chatId(authorId, contactId);

        Chat msg = new Chat();

        msg.setChatId(chatId);
        msg.setAuthorId(authorId);

        return chatMessageRepo.save(msg);
    }

    @Transactional
    public void addContact(User user, String phoneNumber) {
        userRepository.findByPhoneNumber(phoneNumber).ifPresent(user2 -> {
            userRepository.addContact(user.getId(), user2.getId());
        });
    }

    public List<User> getContacts(Integer userId) {
        log.info("Getting contacts not from cache...");
        return userRepository.findAllContacts(userId);
    }
}
