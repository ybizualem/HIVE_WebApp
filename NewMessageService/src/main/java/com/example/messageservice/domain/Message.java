package com.example.messageservice.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "TMESSAGE")

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sender;
    private String receiver;
    private String content;

    @Column(name = "chat_room_id")
    private Long chatRoomId;  // Use just the ID

    public Message() {
    }

    public Message(String sender, String receiver, String content, Long chatRoomId) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.chatRoomId = chatRoomId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }
}
