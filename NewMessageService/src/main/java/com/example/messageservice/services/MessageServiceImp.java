package com.example.messageservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.messageservice.configuration.PusherService;
import com.example.messageservice.dataAccess.MessageRepository;
import com.example.messageservice.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImp implements MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImp.class);

    private final PusherService pusherService;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImp(PusherService pusherService, MessageRepository messageRepository) {
        this.pusherService = pusherService;
        this.messageRepository = messageRepository;
    }

    @Override
    @Transactional
    public void sendMessage(Long chatRoomId, Message message) {
        try {
            // First save the message
            Message savedMessage = messageRepository.save(message);
            // Then trigger real-time update via pusher service
            pusherService.trigger("chat-room-" + chatRoomId, "new-message", savedMessage);
        } catch (Exception e) {
            logger.error("Failed to send message: ", e);
        }
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message saveMessage(Message message) {
        try {
            return messageRepository.save(message);
        } catch (Exception e) {
            logger.error("Failed to save message: ", e);
            throw new RuntimeException("Error saving message: " + e.getMessage());
        }
    }
  @Override
    public List<Message> getMessagesBySender(String sender) {
        return messageRepository.findBySender(sender);
    }
@Override
    public List<Message> getMessagesByReceiver(String receiver) {
        return messageRepository.findByReceiver(receiver);
    }
@Override
    public List<Message> getMessagesByChatRoomId(Long chatRoomId) {
        return messageRepository.findAllByChatRoomId(chatRoomId);
    }

}
