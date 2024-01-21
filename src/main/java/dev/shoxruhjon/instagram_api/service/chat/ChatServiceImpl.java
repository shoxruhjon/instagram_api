package dev.shoxruhjon.instagram_api.service.chat;

import dev.shoxruhjon.instagram_api.dto.response.UserResponse;
import dev.shoxruhjon.instagram_api.entity.ChatEntity;
import dev.shoxruhjon.instagram_api.entity.UserEntity;
import dev.shoxruhjon.instagram_api.repository.ChatRepository;
import dev.shoxruhjon.instagram_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public ChatEntity createChat(UserResponse reqUser, String email) {
        UserEntity userReq = modelMapper.map(reqUser, UserEntity.class);
        UserEntity user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not found"));
        ChatEntity isExist = chatRepository.findChatByUsersId(user, userReq);
        if(isExist != null)
            return isExist;
        ChatEntity chat = new ChatEntity();
        chat.getUsers().add(user);
        chat.getUsers().add(userReq);
        chat.setTimestamp(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    @Override
    public ChatEntity findChatById(Integer chatId) throws Exception {
        Optional<ChatEntity> chat = chatRepository.findById(chatId);
        if(chat.isEmpty())
            throw new Exception("Chat not found with id - " + chatId);
        return chat.get();
    }

    @Override
    public List<ChatEntity> findUsersChat(Integer userId) {
        return chatRepository.findByUsersId(userId);
    }
}
