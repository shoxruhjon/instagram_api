package dev.shoxruhjon.instagram_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "message")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private String image;

    @ManyToOne
    private UserEntity user;

    @JsonIgnore
    @ManyToOne
    private ChatEntity chat;
    private LocalDateTime timestamp;
}
