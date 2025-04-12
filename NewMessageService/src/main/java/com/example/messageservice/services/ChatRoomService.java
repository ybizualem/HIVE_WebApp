package com.example.messageservice.services;

import com.example.messageservice.domain.ChatRoom;
import com.example.messageservice.domain.Message;

import java.util.List;
import java.util.Optional;

public interface ChatRoomService {

    ChatRoom createChatRoom(String senderUsername, String receiverUsername, String chatName);

    void deleteChatRoom(Long id);


    List<ChatRoom> getAllChatRooms();

    List<ChatRoom> getChatRoomsForUser(String username);
    Optional<ChatRoom> findByID(Long chatRoomId);

}
