package com.beas.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beas.entity.ChatMessage;
import com.beas.entity.User;
import com.beas.repo.ChatMessageRepository;
import com.beas.repo.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class ChatController {
    private final UserRepository userRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessageSendingOperations messagingTemplate;

    public ChatController(UserRepository userRepository, ChatMessageRepository chatMessageRepository, SimpMessageSendingOperations messagingTemplate) {
        this.userRepository = userRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        chatMessage.setTimestamp(LocalDateTime.now());
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        user.setOnline(true);
        return userRepository.save(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/online")
    public List<User> getOnlineUsers() {
        return userRepository.findByOnline(true);
    }

    @PostMapping("/users/logout")
    public void logoutUser(@RequestParam String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setOnline(false);
            userRepository.save(user);
            messagingTemplate.convertAndSend("/topic/public", new ChatMessage("User " + username + " has logged out", "System", LocalDateTime.now()));
        }
    }
}
