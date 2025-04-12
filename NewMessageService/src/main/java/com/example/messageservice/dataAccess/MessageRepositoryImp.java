//package com.example.messageservice.dataAccess;
//
//
//import com.example.messageservice.domain.Message;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class MessageRepositoryImp implements MessageRepository {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    @Transactional
//    public Message save(Message message) {
//        if (message.getId() != null && entityManager.find(Message.class, message.getId()) != null) {
//            message = entityManager.merge(message);
//        } else {
//            entityManager.persist(message);
//        }
//        return message;
//    }
//
//    @Override
//    public List<Message> findAll() {
//        return entityManager.createQuery("SELECT m FROM Message m", Message.class).getResultList();
//    }
//
//    @Override
//    public Optional<Message> findById(Long id) {
//        Message message = entityManager.find(Message.class, id);
//        return Optional.ofNullable(message);
//    }
//
//    @Override
//    @Transactional
//    public void deleteById(Long id) {
//        Optional<Message> message = findById(id);
//        message.ifPresent(entityManager::remove);
//    }
//
//    @Override
//    public List<Message> findBySender(String sender) {
//        return entityManager.createQuery("SELECT m FROM Message m WHERE m.sender = :sender", Message.class)
//                .setParameter("sender", sender)
//                .getResultList();
//    }
//
//    @Override
//    public List<Message> findByReceiver(String receiver) {
//        return entityManager.createQuery("SELECT m FROM Message m WHERE m.receiver = :receiver", Message.class)
//                .setParameter("receiver", receiver)
//                .getResultList();
//    }
//}
//
//
