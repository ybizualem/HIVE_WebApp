package com.example.messageservice.controllers;

import com.example.messageservice.dataAccess.CreateChatRoomRequest;
import com.example.messageservice.domain.ChatRoom;
import com.example.messageservice.domain.Message;
import com.example.messageservice.services.ChatRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.messageservice.Utlities.MatchedUsersRequest;

import java.util.List;
@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {
    private static final Logger logger = LoggerFactory.getLogger(ChatRoomController.class);
    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @GetMapping
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomService.getAllChatRooms();
    }

    @GetMapping("/user/{username}")
    public List<ChatRoom> getChatRoomsForUser(@PathVariable String username) {
        return chatRoomService.getChatRoomsForUser(username);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ChatRoom> getChatRoomById(@PathVariable Long id) {
        return chatRoomService.findByID(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ChatRoom createChatRoom(@RequestBody CreateChatRoomRequest request) {
        return chatRoomService.createChatRoom(request.getSenderUsername(), request.getReceiverUsername(),request.getname());
    }

    @DeleteMapping("/{id}")
    public void deleteChatRoom(@PathVariable Long id) {
        chatRoomService.deleteChatRoom(id);
    }

    @PostMapping("/create-for-match")
    public ResponseEntity<ChatRoom> createChatRoomForMatchedUsers(@RequestBody MatchedUsersRequest matchedUsers) {
        logger.info("Received request to create chat room for users: {} and {}", matchedUsers.getUser1(), matchedUsers.getUser2());
        try {
            ChatRoom createdChatRoom = chatRoomService.createChatRoom(matchedUsers.getUser1(), matchedUsers.getUser2(), "Generated Room Name");
            logger.info("Successfully created chat room for users: {} and {}", matchedUsers.getUser1(), matchedUsers.getUser2());
            return ResponseEntity.ok(createdChatRoom);
        } catch (Exception e) {
            logger.error("Failed to create chat room: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
