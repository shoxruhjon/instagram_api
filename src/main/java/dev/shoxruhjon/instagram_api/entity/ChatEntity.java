package dev.shoxruhjon.instagram_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "chat")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String chatName;
    private String chatImage;

    @ManyToMany
    private List<UserEntity> users = new ArrayList<>();
    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "chat")
    private List<MessageEntity> messages = new ArrayList<>();
}
