package dev.shoxruhjon.instagram_api.service.comment;

import dev.shoxruhjon.instagram_api.entity.CommentEntity;
import dev.shoxruhjon.instagram_api.entity.PostEntity;
import dev.shoxruhjon.instagram_api.entity.UserEntity;
import dev.shoxruhjon.instagram_api.repository.CommentRepository;
import dev.shoxruhjon.instagram_api.repository.PostRepository;
import dev.shoxruhjon.instagram_api.service.post.PostService;
import dev.shoxruhjon.instagram_api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final PostService postService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    @Override
    public CommentEntity createComment(CommentEntity comment, Integer postId, Integer userId) {
        UserEntity user = userService.findUserById(userId);
        PostEntity post = postService.findPostById(postId);
        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        CommentEntity savedComment = commentRepository.save(comment);
        post.getComments().add(savedComment);
        postRepository.save(post);
        return savedComment;
    }

    @Override
    public CommentEntity findCommentById(Integer commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not exist"));
    }

    @Override
    public CommentEntity likedComment(Integer commentId, Integer userId) {
        CommentEntity comment = findCommentById(commentId);
        UserEntity user = userService.findUserById(userId);
        if(!comment.getLiked().contains(user))
            comment.getLiked().add(user);
        else
            comment.getLiked().remove(user);
        return commentRepository.save(comment);
    }
}
