package com.example.messageservice.dataAccess;
import com.example.messageservice.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Finds chat rooms where the sender or receiver username matches the provided usernames.
 */

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
     List<ChatRoom> findBySenderUsernameOrReceiverUsername(String senderUsername, String receiverUsername);
     Optional<ChatRoom> findById(Long id);
}
