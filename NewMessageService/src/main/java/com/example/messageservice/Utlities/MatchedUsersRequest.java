package com.example.messageservice.Utlities;

import jakarta.validation.constraints.NotBlank;

public class MatchedUsersRequest {
    @NotBlank(message = "User1 username cannot be blank")
    private String user1;
    @NotBlank(message = "User2 username cannot be blank")
    private String user2;

    public MatchedUsersRequest(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    // Constructors, getters, and setters
}
