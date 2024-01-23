package dev.shoxruhjon.instagram_api.service.comment;

import dev.shoxruhjon.instagram_api.dto.request.CommentCreateDto;
import dev.shoxruhjon.instagram_api.dto.response.CommentResponse;
import dev.shoxruhjon.instagram_api.entity.CommentEntity;
import org.springframework.stereotype.Service;

public interface CommentService {
    public CommentResponse createComment(CommentCreateDto comment, Integer postId, Integer userId);
    public CommentEntity findCommentById(Integer commentId);
    public CommentEntity likedComment(Integer commentId, Integer userId);
}
