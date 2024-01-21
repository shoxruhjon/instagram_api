package dev.shoxruhjon.instagram_api.controller;

import dev.shoxruhjon.instagram_api.entity.ReelsEntity;
import dev.shoxruhjon.instagram_api.service.reels.ReelsService;
import dev.shoxruhjon.instagram_api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reels")
@RequiredArgsConstructor
public class ReelsController {

    private final ReelsService reelsService;
    private final UserService userService;

    @PostMapping ("/")
    public ResponseEntity<ReelsEntity> createReels(@RequestBody ReelsEntity reels,
                                                   @RequestHeader("Authorization") String jwt){
        return new ResponseEntity<>(reelsService.createReel(
                reels, userService.findUserByJwt(jwt)), HttpStatus.ACCEPTED);
    }

    @GetMapping("/reels")
    public ResponseEntity<List<ReelsEntity>> findAllReels() {
        return new ResponseEntity<>(
                reelsService.findAllReels(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReelsEntity>> findUserReels(@PathVariable Integer userId){
        return new ResponseEntity<>(reelsService.findUserReel(userId), HttpStatus.OK);
    }
}
