package dev.shoxruhjon.instagram_api.repository;

import dev.shoxruhjon.instagram_api.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    List<MessageEntity> findByChatId(Integer chatId);
}