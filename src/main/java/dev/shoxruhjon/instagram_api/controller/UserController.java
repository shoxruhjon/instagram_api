package dev.shoxruhjon.instagram_api.controller;

import dev.shoxruhjon.instagram_api.entity.UserEntity;
import dev.shoxruhjon.instagram_api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Integer userId){
        return new ResponseEntity<>(
                userService.findUserById(userId), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> getUsers(){
        return new ResponseEntity<>(
                userService.findAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<UserEntity> updateUser(@RequestHeader("Authorization") String jwt,
                                                 @RequestBody UserEntity user) {
        return new ResponseEntity<>(
                userService.updateUser(
                        user, userService.findUserByJwt(jwt).getId()), HttpStatus.ACCEPTED);
    }

    @PutMapping("/follow/{userId}")
    public ResponseEntity<UserEntity> followUserHandler(@RequestHeader("Authorization") String jwt,
                                                        @PathVariable Integer userId){
        return new ResponseEntity<>(userService.followUser(
                userService.findUserByJwt(jwt).getId(), userId), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserEntity>> searchUser(@RequestParam("query") String query){
        return new ResponseEntity<>(userService.searchUser(query), HttpStatus.OK);
    }
}
