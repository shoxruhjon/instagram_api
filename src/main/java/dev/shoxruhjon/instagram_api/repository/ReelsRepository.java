package dev.shoxruhjon.instagram_api.repository;

import dev.shoxruhjon.instagram_api.entity.ChatEntity;
import dev.shoxruhjon.instagram_api.entity.ReelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReelsRepository extends JpaRepository<ReelsEntity, Integer> {
    public List<ReelsEntity> findByUserId(Integer userId);
}