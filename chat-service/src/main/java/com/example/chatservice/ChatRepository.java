package com.example.chatservice;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ChatRepository extends JpaRepository<Chat, Integer> {

    List<Chat> findAllByChatIdOrderByCreatedAtDesc(String chatId, Pageable pageable);
}
