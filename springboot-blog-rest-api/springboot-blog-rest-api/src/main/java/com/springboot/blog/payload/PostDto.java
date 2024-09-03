package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(
        description = "PostDto Model information"
)
public class PostDto {
    private long id;
    //Java bean validation

    @Schema(
            description = "Blog Post Title"
    )
    //title should not be null or empty
    //title's size should have at least 2 characters
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 charaters")
    private String title;

    @Schema(
            description = "Blog Post description"
    )
    //Description should not be null or empty
    // size should have at least 10 characters
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    @Schema(
            description = "Blog Post content"
    )
    //Content should not be null or empty
    @NotEmpty
    @Size(message = "Content must not be empty")
    private String content;
    private Set<CommentDto> comments;

    @Schema(
            description = "Blog Post Category"
    )
    private Long categoryId;
}
