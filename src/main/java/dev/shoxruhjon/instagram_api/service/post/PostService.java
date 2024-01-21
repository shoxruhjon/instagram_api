package dev.shoxruhjon.instagram_api.service.post;

import dev.shoxruhjon.instagram_api.entity.PostEntity;
import dev.shoxruhjon.instagram_api.dto.response.ApiResponse;

import java.util.List;

public interface PostService {

    PostEntity createNewPost(PostEntity post, Integer userId);
    ApiResponse deletePost(Integer postId, Integer userId);
    List<PostEntity> findPostByUserId(Integer userId);
    PostEntity findPostById(Integer postId);
    List<PostEntity> findAllPost();
    PostEntity savedPost(Integer postId, Integer userId);
    PostEntity likePost(Integer postId, Integer userId);
}
