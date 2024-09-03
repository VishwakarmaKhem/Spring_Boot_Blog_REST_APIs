package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(
        description = "CommentDto Model information"
)
public class CommentDto {
    private long id;

    @Schema(
            description = "Commentator name"
    )
    @NotEmpty(message = "Name must not be empty")
    private String name;

    @Schema(
            description = "Commentator email"
    )
    @NotEmpty(message = "Email must not be empty")
    @Email
    private String email;

    @Schema(
            description = "Comment body or the actual comment"
    )
    @NotEmpty(message = "Body must not be empty")
    private String body;
}
