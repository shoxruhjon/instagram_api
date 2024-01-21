package dev.shoxruhjon.instagram_api.service.chat;

import dev.shoxruhjon.instagram_api.dto.response.UserResponse;
import dev.shoxruhjon.instagram_api.entity.ChatEntity;
import dev.shoxruhjon.instagram_api.entity.UserEntity;

import java.util.List;

public interface ChatService {
    public ChatEntity createChat(UserResponse reqUser, String email);
    public ChatEntity findChatById(Integer chatId) throws Exception;
    public List<ChatEntity> findUsersChat(Integer userId);
}
