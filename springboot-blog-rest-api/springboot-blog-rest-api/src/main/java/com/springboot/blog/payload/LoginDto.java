package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "LoginDto Model information"
)
public class LoginDto {
    @Schema(
            description = "Username or Email"
    )
    private String usernameOrEmail;

    @Schema(
            description = "Password"
    )
    private String password;
}
