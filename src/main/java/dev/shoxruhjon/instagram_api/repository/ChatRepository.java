package dev.shoxruhjon.instagram_api.repository;

import dev.shoxruhjon.instagram_api.entity.ChatEntity;
import dev.shoxruhjon.instagram_api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {
    public List<ChatEntity> findByUsersId(Integer userId);

    @Query("select c from chat c where :user member of c.users and :reqUser member of c.users")
    public ChatEntity findChatByUsersId(@Param("user")UserEntity user, @Param("reqUser") UserEntity reqUser);
}