package dev.shoxruhjon.instagram_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentCreateDto implements Serializable {
    @NotBlank
    String content;
}