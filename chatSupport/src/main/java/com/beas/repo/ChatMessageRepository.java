package com.beas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beas.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}
