package com.example.messageservice.services;

import com.example.messageservice.dataAccess.ChatRoomRepository;
import com.example.messageservice.dataAccess.MessageRepository;
import com.example.messageservice.domain.ChatRoom;
import com.example.messageservice.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MessageService messageService;

    @Autowired
    public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository, MessageService messageService) {
        this.chatRoomRepository = chatRoomRepository;
        this.messageService = messageService;
    }

    @Override
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    @Override
    public List<ChatRoom> getChatRoomsForUser(String username) {
        return chatRoomRepository.findBySenderUsernameOrReceiverUsername(username, username);
    }

    @Override
    public ChatRoom createChatRoom(String senderUsername, String receiverUsername, String chatName) {
        ChatRoom chatRoom = new ChatRoom(senderUsername, receiverUsername,chatName);
         chatRoomRepository.save(chatRoom);
        sendMessageToChatRoom(chatRoom.getId(), "Welcome",senderUsername, receiverUsername);
        return chatRoom;
    }
    private void sendMessageToChatRoom(Long chatRoomId, String messageText, String senderUsername, String receiverUsername) {
        Message message = new Message(senderUsername,receiverUsername,messageText,chatRoomId);
        messageService.saveMessage(message);
        // Additional logic to send the message to the users
    }

        @Override
    public void deleteChatRoom(Long chatRoomId) {
        chatRoomRepository.deleteById(chatRoomId);
    }
    @Override
    public Optional<ChatRoom> findByID(Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId);
    }




}
