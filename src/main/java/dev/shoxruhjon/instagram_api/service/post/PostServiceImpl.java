package dev.shoxruhjon.instagram_api.service.post;

import dev.shoxruhjon.instagram_api.entity.PostEntity;
import dev.shoxruhjon.instagram_api.entity.UserEntity;
import dev.shoxruhjon.instagram_api.repository.PostRepository;
import dev.shoxruhjon.instagram_api.repository.UserRepository;
import dev.shoxruhjon.instagram_api.dto.response.ApiResponse;
import dev.shoxruhjon.instagram_api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public PostEntity createNewPost(PostEntity post, Integer userId) {
        UserEntity user = userService.findUserById(userId);
        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    public ApiResponse deletePost(Integer postId, Integer userId) {
        PostEntity post = findPostById(postId);
        UserEntity user = userService.findUserById(userId);
        if(!Objects.equals(post.getUser().getId(), user.getId()))
            throw new RuntimeException("You cannot delete another users post");
        postRepository.delete(post);
        return new ApiResponse("Post deleted Successfully", true);
    }

    @Override
    public List<PostEntity> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public PostEntity findPostById(Integer postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id : " + postId));
    }

    @Override
    public List<PostEntity> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public PostEntity savedPost(Integer postId, Integer userId) {
        PostEntity post = findPostById(postId);
        UserEntity user = userService.findUserById(userId);
        if (user.getSavedPost().contains(post))
            user.getSavedPost().remove(post);
        else
            user.getSavedPost().add(post);
        userRepository.save(user);
        return post;
    }

    @Override
    public PostEntity likePost(Integer postId, Integer userId) {
        PostEntity post = findPostById(postId);
        UserEntity user = userService.findUserById(userId);

        if (post.getLiked().contains(user))
            post.getLiked().remove(user);
        else
            post.getLiked().add(user);
        return postRepository.save(post);
    }
}
