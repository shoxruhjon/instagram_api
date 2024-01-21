package dev.shoxruhjon.instagram_api.service.comment;

import dev.shoxruhjon.instagram_api.entity.CommentEntity;
import org.springframework.stereotype.Service;

public interface CommentService {
    public CommentEntity createComment(CommentEntity comment, Integer postId, Integer userId);
    public CommentEntity findCommentById(Integer commentId);
    public CommentEntity likedComment(Integer commentId, Integer userId);
}
