package com.example.chatservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChatControllerTest {

    @Mock
    private ChatService chatService;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    private ChatController chatController;

    @BeforeEach
    public void setup() {
        chatController = new ChatController(chatService);
        chatController.messagingTemplate = messagingTemplate;
    }

    @Test
    public void testChatMessages() {
        User user = new User();
        String chatId = "test-chat-id";

        Chat chat = new Chat();
        chat.setChatId(chatId);

        when(chatService.getLastMessages(anyString())).thenReturn(Collections.singletonList(chat));

        List<Chat> result = chatController.chatMessages(user, chatId);

        verify(chatService).getLastMessages(chatId);
        verifyNoMoreInteractions(chatService);

        assert result != null;
        assert result.size() == 1;
        assert result.get(0).getChatId().equals(chatId);
    }

    @Test
    public void testSendMessage() {
        User user = new User();
        user.setId(1);
        Long contactId = 2L;

        Chat chat = new Chat();
        chat.setChatId(Utils.chatId(user.getId(), contactId));

        when(chatService.saveMessage(anyInt(), anyLong())).thenReturn(chat);

        chatController.sendMessage(contactId, user);

        verify(chatService).saveMessage(eq(user.getId()), eq(contactId));
        verify(messagingTemplate).convertAndSend(eq("/topic/chat/" + chat.getChatId() + "/messages"), anyList());
        verifyNoMoreInteractions(chatService, messagingTemplate);
    }
}
