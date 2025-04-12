package com.example.messageservice.Utlities;

import com.example.messageservice.domain.User;
import com.example.messageservice.Utlities.UserDTO;

public class UserMapper {

    // Convert from User entity to User DTO
    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(user.getId(), user.getUsername());
    }

    // Convert from User DTO to User entity
    public static User fromDTO(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        return user;
    }
}
