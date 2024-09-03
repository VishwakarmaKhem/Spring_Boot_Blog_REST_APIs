package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "RegisterDto Model information"
)
public class RegisterDto {
    @Schema(
            description = "New User's name"
    )
    private String name;

    @Schema(
            description = "New User's username"
    )
    private String username;

    @Schema(
            description = "New User's email"
    )
    private String email;

    @Schema(
            description = "New User's password"
    )
    private String password;
}
