package dev.shoxruhjon.instagram_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "reels")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReelsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String video;
    @ManyToOne
    private UserEntity user;
}
