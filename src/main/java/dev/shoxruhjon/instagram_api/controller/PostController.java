package dev.shoxruhjon.instagram_api.controller;

import dev.shoxruhjon.instagram_api.entity.PostEntity;
import dev.shoxruhjon.instagram_api.dto.response.ApiResponse;
import dev.shoxruhjon.instagram_api.service.post.PostService;
import dev.shoxruhjon.instagram_api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<PostEntity> createPost(
            @RequestHeader("Authorization") String jwt,
            @RequestBody PostEntity post){
        return new ResponseEntity<>(postService.createNewPost(
                post,
                userService.findUserByJwt(jwt).getId()),
                HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,
                                                  @RequestHeader("Authorization") String jwt){
        return new ResponseEntity<>(postService.deletePost(
                postId,
                userService.findUserByJwt(jwt).getId()),
                HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostEntity> findPostByIdHandler(@PathVariable Integer postId){
        return new ResponseEntity<>(postService.findPostById(postId),HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostEntity>> findUsersPost(@PathVariable Integer userId){
        return new ResponseEntity<>(postService.findPostByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<PostEntity>> findAllPosts(){
        return new ResponseEntity<>(postService.findAllPost(), HttpStatus.OK);
    }

    @PutMapping("/save/{postId}")
    public ResponseEntity<PostEntity> savedPostHandler(@PathVariable Integer postId,
                                                       @RequestHeader("Authorization") String jwt){
        return new ResponseEntity<>(postService.savedPost(
                postId,
                userService.findUserByJwt(jwt).getId()),
                HttpStatus.ACCEPTED);
    }

    @PutMapping("/like/{postId}/user/{userId}")
    public ResponseEntity<PostEntity> likedPostHandler(@PathVariable Integer postId, @PathVariable Integer userId){
        return new ResponseEntity<>(postService.likePost(postId, userId), HttpStatus.ACCEPTED);
    }
}
