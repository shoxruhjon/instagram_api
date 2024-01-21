package dev.shoxruhjon.instagram_api.repository;

import dev.shoxruhjon.instagram_api.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    @Query("select p from post p where p.user.id = :userId")
    List<PostEntity> findPostByUserId(Integer userId);
}
