package dev.shoxruhjon.instagram_api.service.message;

import dev.shoxruhjon.instagram_api.dto.response.UserResponse;
import dev.shoxruhjon.instagram_api.entity.ChatEntity;
import dev.shoxruhjon.instagram_api.entity.MessageEntity;
import dev.shoxruhjon.instagram_api.entity.UserEntity;
import dev.shoxruhjon.instagram_api.repository.ChatRepository;
import dev.shoxruhjon.instagram_api.repository.MessageRepository;
import dev.shoxruhjon.instagram_api.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChatService chatService;
    private final ChatRepository chatRepository;
    private final ModelMapper modelMapper;

    @Override
    public MessageEntity createMessage(UserResponse user, Integer chatId, MessageEntity message) throws Exception {
        UserEntity userReq = modelMapper.map(user, UserEntity.class);
        ChatEntity chat = chatService.findChatById(chatId);
        message.setChat(chat);
        message.setUser(userReq);
        message.setTimestamp(LocalDateTime.now());

        MessageEntity savedMessage = messageRepository.save(message);
        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);
        return savedMessage;
    }

    @Override
    public List<MessageEntity> findChatsMessages(Integer chatId) {
        return messageRepository.findByChatId(chatId);
    }
}
