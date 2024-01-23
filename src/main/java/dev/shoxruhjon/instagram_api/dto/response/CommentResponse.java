package dev.shoxruhjon.instagram_api.dto.response;

import dev.shoxruhjon.instagram_api.entity.UserEntity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentResponse implements Serializable {
    Integer id;
    String content;
    UserEntity user;
    List<UserEntity> liked;
    LocalDateTime createdAt;
}