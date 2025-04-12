package com.example.messageservice.Utlities;


import com.example.messageservice.domain.User;
import com.example.messageservice.Utlities.UserDTO;
import com.example.messageservice.dataAccess.UserRepository;
import com.example.messageservice.Utlities.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDtoService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO findUserById(Long id) {
        Optional<User> user = userRepository.findById(String.valueOf(id));
        return user.map(UserMapper::toDTO).orElse(null);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.fromDTO(userDTO);
        user = userRepository.save(user);
        return UserMapper.toDTO(user);
    }
}

