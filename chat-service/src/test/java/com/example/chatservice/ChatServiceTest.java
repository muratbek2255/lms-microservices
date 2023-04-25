package com.example.chatservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChatServiceTest {

    @Mock
    private ChatRepository chatMessageRepo;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ChatService chatService;

    @Test
    public void testGetLastMessages() {
        // Given
        String chatId = "test-chat-id";
        Chat chat1 = new Chat();
        chat1.setChatId(chatId);
        chat1.setCreatedAt(Timestamp.from(Instant.now()));
        chat1.setAuthorId(1);
        Chat chat2 = new Chat();
        chat2.setChatId(chatId);
        chat2.setCreatedAt(Timestamp.from(Instant.now()));
        chat2.setAuthorId(2);
        List<Chat> chatList = List.of(chat1, chat2);
        when(chatMessageRepo.findAllByChatIdOrderByCreatedAtDesc(eq(chatId), any())).thenReturn(chatList);

        // When
        List<Chat> result = chatService.getLastMessages(chatId);

        // Then
        assertThat(result).isEqualTo(chatList);
        verify(chatMessageRepo).findAllByChatIdOrderByCreatedAtDesc(eq(chatId), any());
    }

    @Test
    public void testSaveMessage() {
        // Given
        int authorId = 1;
        long contactId = 2L;
        String chatId = "1-2";
        Chat msg = new Chat();
        msg.setChatId(chatId);
        msg.setAuthorId(authorId);
        when(chatMessageRepo.save(any())).thenReturn(msg);

        // When
        Chat result = chatService.saveMessage(authorId, contactId);

        // Then
        assertThat(result.getChatId()).isEqualTo(chatId);
        assertThat(result.getAuthorId()).isEqualTo(authorId);
        verify(chatMessageRepo).save(any());
    }

    @Test
    public void testAddContact() {
        // Given
        User user = new User();
        user.setId(1);
        User user2 = new User();
        user2.setId(2);
        String phoneNumber = "123456789";
        when(userRepository.findByPhoneNumber(phoneNumber)).thenReturn(java.util.Optional.of(user2));
        doNothing().when(userRepository).addContact(eq(user.getId()), eq(user2.getId()));

        // When
        chatService.addContact(user, phoneNumber);

        // Then
        verify(userRepository).findByPhoneNumber(phoneNumber);
        verify(userRepository).addContact(eq(user.getId()), eq(user2.getId()));
    }

    @Test
    public void testGetContacts() {
        // Given
        int userId = 1;
        User user1 = new User();
        user1.setId(2);
        User user2 = new User();
        user2.setId(3);
        List<User> userList = List.of(user1, user2);
        when(userRepository.findAllContacts(userId)).thenReturn(userList);

        // When
        List<User> result = chatService.getContacts(userId);

        // Then
        assertThat(result).isEqualTo(userList);
        verify(userRepository).findAllContacts(userId);
    }
}
