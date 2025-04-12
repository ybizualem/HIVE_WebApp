package com.example.messageservice.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "CHATROOM")

public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String senderUsername;
    private String receiverUsername;

    // Constructors
    public ChatRoom() {
    }

    public ChatRoom(String senderUsername, String receiverUsername, String name) {
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }
}
