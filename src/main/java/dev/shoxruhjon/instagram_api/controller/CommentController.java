package dev.shoxruhjon.instagram_api.controller;

import dev.shoxruhjon.instagram_api.entity.CommentEntity;
import dev.shoxruhjon.instagram_api.service.comment.CommentService;
import dev.shoxruhjon.instagram_api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentEntity> createComment(@RequestBody CommentEntity comment,
                                                       @RequestHeader("Authorization") String jwt,
                                                       @PathVariable("postId") Integer postId){
        return new ResponseEntity<>(commentService.createComment(
                comment, postId, userService.findUserByJwt(jwt).getId()), HttpStatus.CREATED);
    }

    @PutMapping("/like/{commentId}")
    public ResponseEntity<CommentEntity> likeComment(@RequestHeader("Authorization") String jwt,
                                                     @PathVariable Integer commentId){
        return new ResponseEntity<>(commentService.likedComment(
                commentId, userService.findUserByJwt(jwt).getId()), HttpStatus.OK);
    }
}
