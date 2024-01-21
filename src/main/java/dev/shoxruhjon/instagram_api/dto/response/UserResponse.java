package dev.shoxruhjon.instagram_api.dto.response;

import dev.shoxruhjon.instagram_api.entity.PostEntity;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponse implements Serializable {
    Integer id;
    String firstName;
    String lastName;
    @Email
    String email;
    String password;
    String gender;
    List<List<Integer>> followers;
    List<List<Integer>> followings;
    List<PostEntity> savedPost;
}