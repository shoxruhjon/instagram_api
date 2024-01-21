package dev.shoxruhjon.instagram_api.service.message;

import dev.shoxruhjon.instagram_api.dto.response.UserResponse;
import dev.shoxruhjon.instagram_api.entity.MessageEntity;

import java.util.List;

public interface MessageService {
    public MessageEntity createMessage(UserResponse user, Integer chatId, MessageEntity message) throws Exception;
    public List<MessageEntity> findChatsMessages(Integer chatId);
}
