package com.example.chatservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @SubscribeMapping("/chat/{chatId}/messages")
    public List<Chat> chatMessages(
            @AuthenticationPrincipal User user,
            @DestinationVariable("chatId") String chatId
    ) {

        return chatService.getLastMessages(chatId);
    }

    @MessageMapping("/chat/{contactId}/messages/add")
    public void sendMessage(
            @DestinationVariable("contactId") Long contactId,
            @AuthenticationPrincipal User user
    ) {
        String chatId = Utils.chatId(user.getId(), contactId);
        Chat msg = chatService.saveMessage(user.getId(), contactId);

        messagingTemplate.convertAndSend("/topic/chat/" + chatId + "/messages", List.of(msg));
    }
}
