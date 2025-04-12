package com.example.messageservice.configuration;
import com.example.messageservice.dataAccess.ChatRoomRepository;
import com.example.messageservice.dataAccess.MessageRepository;
import com.example.messageservice.domain.ChatRoom;
import com.example.messageservice.domain.Message;
import com.example.messageservice.domain.User;
import com.example.messageservice.dataAccess.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataSetupConfiguration {

    @Bean
    CommandLineRunner initDatabase(MessageRepository messageRepository, ChatRoomRepository chatRoomRepository, UserRepository userRepository) {
        return args -> {
            // create user
            User user1 = new User(1L,"Alice");
            User user2 = new User(2L,"Bob");
            User user3 = new User(3L,"Charlie");
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            // Create chat rooms
            ChatRoom chatRoom1 = new ChatRoom(user1.getUsername(), user2.getUsername(),"chatRoom1");
            ChatRoom chatRoom2 = new ChatRoom(user1.getUsername(), user3.getUsername(),"chatRoom2");
            chatRoomRepository.save(chatRoom1);
            chatRoomRepository.save(chatRoom2);

            // Create messages
            Message msg1 = new Message(user1.getUsername(), user2.getUsername(), "Hello Bob!", chatRoom1.getId());
            Message msg2 = new Message(user2.getUsername(), user1.getUsername(), "Hey Alice!", chatRoom1.getId());
            Message msg3 = new Message(user1.getUsername(),user3.getUsername(), "Are you there Charlie?", chatRoom2.getId());
            messageRepository.save(msg1);
            messageRepository.save(msg2);
            messageRepository.save(msg3);
        };
    }
}
