package com.example.messageservice.services;

import com.example.messageservice.domain.Message;
import java.util.List;

public interface MessageService {
    /**
     * Sends a message to a specified chat room using a real-time messaging service.
     * @param chatRoomId the ID of the chat room.
     * @param message the message to be sent.
     */
    void sendMessage(Long chatRoomId, Message message);

    /**
     * Retrieves all messages from the database.
     * @return a list of all messages.
     */
    List<Message> getAllMessages();

    /**
     * Saves a given message into the database.
     * @param message the message to save.
     * @return the saved message.
     */
    Message saveMessage(Message message);

    /**
     * Retrieves messages sent by a specific sender.
     * @param sender the sender's identifier.
     * @return a list of messages sent by the sender.
     */
    List<Message> getMessagesBySender(String sender);

    /**
     * Retrieves messages sent to a specific receiver.
     * @param receiver the receiver's identifier.
     * @return a list of messages received by the receiver.
     */
    List<Message> getMessagesByReceiver(String receiver);

    /**
     * Retrieves messages associated with a specific chat room ID.
     * @param chatRoomId the ID of the chat room.
     * @return a list of messages associated with the chat room.
     */
    List<Message> getMessagesByChatRoomId(Long chatRoomId);
}
