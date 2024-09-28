package com.gslab.blog_Application.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserRequest {
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    private String password;

    private String about;

}
