package dev.shoxruhjon.instagram_api.service.comment;

import dev.shoxruhjon.instagram_api.dto.request.CommentCreateDto;
import dev.shoxruhjon.instagram_api.dto.response.CommentResponse;
import dev.shoxruhjon.instagram_api.entity.CommentEntity;
import dev.shoxruhjon.instagram_api.entity.PostEntity;
import dev.shoxruhjon.instagram_api.entity.UserEntity;
import dev.shoxruhjon.instagram_api.repository.CommentRepository;
import dev.shoxruhjon.instagram_api.repository.PostRepository;
import dev.shoxruhjon.instagram_api.service.post.PostService;
import dev.shoxruhjon.instagram_api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final PostService postService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public CommentResponse createComment(CommentCreateDto dto, Integer postId, Integer userId) {
        UserEntity user = userService.findUserById(userId);
        PostEntity post = postService.findPostById(postId);
        CommentEntity comment = modelMapper.map(dto, CommentEntity.class);
        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());
        CommentEntity savedComment = commentRepository.save(comment);
        post.getComments().add(savedComment);
        postRepository.save(post);
        return modelMapper.map(savedComment, CommentResponse.class);
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
