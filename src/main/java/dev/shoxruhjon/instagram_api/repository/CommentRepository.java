package dev.shoxruhjon.instagram_api.repository;

import dev.shoxruhjon.instagram_api.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}