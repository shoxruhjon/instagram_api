package dev.shoxruhjon.instagram_api.controller;

import dev.shoxruhjon.instagram_api.entity.ChatEntity;
import dev.shoxruhjon.instagram_api.entity.UserEntity;
import dev.shoxruhjon.instagram_api.service.chat.ChatService;
import dev.shoxruhjon.instagram_api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<ChatEntity> createChat(@RequestHeader("Authorization") String jwt,
                                                 @RequestBody String email){
        return new ResponseEntity<>(chatService.createChat(
                userService.findUserByJwt(jwt), email), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ChatEntity>> findUsersChat(@RequestHeader("Authorization") String jwt){
        return new ResponseEntity<>(chatService.findUsersChat(
                userService.findUserByJwt(jwt).getId()), HttpStatus.OK);
    }
}
