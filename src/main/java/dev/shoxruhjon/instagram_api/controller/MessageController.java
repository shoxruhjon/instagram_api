package dev.shoxruhjon.instagram_api.controller;

import dev.shoxruhjon.instagram_api.entity.MessageEntity;
import dev.shoxruhjon.instagram_api.service.message.MessageService;
import dev.shoxruhjon.instagram_api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
    private MessageService messageService;
    private UserService userService;

    @PostMapping("/chat/{chatId}")
    public ResponseEntity<MessageEntity> createMessage(@RequestBody MessageEntity message,
                                                       @RequestHeader("Authorization")String jwt,
                                                       @PathVariable Integer chatId) throws Exception {
        return new ResponseEntity<>(messageService.createMessage(
                userService.findUserByJwt(jwt), chatId, message), HttpStatus.ACCEPTED);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<MessageEntity>> findChatsMessage(@RequestHeader("Authorization") String jwt,
                                                @PathVariable Integer chatId) {
        return new ResponseEntity<>(messageService.findChatsMessages(chatId), HttpStatus.OK);
    }
}
