/*
 * This interface declares supporting methods.
 */
package com.example.messageservice.dataAccess;
import com.example.messageservice.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySender(String sender);
    List<Message> findByReceiver(String receiver);
    List<Message> findAllByChatRoomId(Long chatRoomId);

}