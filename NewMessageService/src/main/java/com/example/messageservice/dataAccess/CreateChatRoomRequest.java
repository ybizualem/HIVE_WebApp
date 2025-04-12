package com.example.messageservice.dataAccess;

public class CreateChatRoomRequest {
    private String senderUsername;
    private String receiverUsername;
    private String chatRoomName;

    // Getters and setters
    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getname() {
        return chatRoomName;
    }
}
