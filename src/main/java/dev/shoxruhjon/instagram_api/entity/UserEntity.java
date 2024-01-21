package dev.shoxruhjon.instagram_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;
    private String gender;
    private List<Integer> followers = new ArrayList<>();
    private List<Integer> followings = new ArrayList<>();

    @ManyToMany
    private List<PostEntity> savedPost = new ArrayList<>();
}
