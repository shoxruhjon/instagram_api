package dev.shoxruhjon.instagram_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCreateDto implements Serializable {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String gender;
}