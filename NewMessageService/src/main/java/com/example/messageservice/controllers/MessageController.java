package com.example.messageservice.controllers;

import com.example.messageservice.domain.Message;
import com.example.messageservice.services.MessageService;
import com.example.messageservice.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final MessageService messageService;
    private final ChatRoomService chatRoomService;

    @Autowired
    public MessageController(MessageService messageService, ChatRoomService chatRoomService) {
        this.messageService = messageService;
        this.chatRoomService = chatRoomService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        return chatRoomService.findByID(message.getChatRoomId())
                .map(chatRoom -> {
                    try {
                        Message savedMessage = messageService.saveMessage(message);
                        messageService.sendMessage(message.getChatRoomId(), savedMessage); // Directly using the chatRoomId from message
                        return ResponseEntity.ok(savedMessage);
                    } catch (Exception e) {
                        logger.error("Failed to send and save message for ChatRoom ID {}: {}", message.getChatRoomId(), e.getMessage(), e);
                        return ResponseEntity.internalServerError().body("Failed to send and save message: " + e.getMessage());
                    }
                })
                .orElseGet(() -> {
                    logger.error("ChatRoom not found for ID: {}", message.getChatRoomId());
                    return ResponseEntity.notFound().build();
                });
    }

    @GetMapping("/{chatRoomId}")
    public ResponseEntity<List<Message>> getMessagesByChatRoomId(@PathVariable Long chatRoomId) {
        List<Message> messages = messageService.getMessagesByChatRoomId(chatRoomId);
        if (messages.isEmpty()) {
            logger.info("No messages found for ChatRoom ID: {}", chatRoomId);
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(messages);
        }
    }

}
