package dev.shoxruhjon.instagram_api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String caption;
    private String image;
    private String video;

    @ManyToOne
    private UserEntity user;

    @OneToMany
    private List<UserEntity> liked = new ArrayList<>();
    private LocalDateTime createdAt;

    @OneToMany
    private List<CommentEntity> comments = new ArrayList<>();
}
