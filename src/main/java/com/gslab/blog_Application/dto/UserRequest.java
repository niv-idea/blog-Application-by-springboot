package com.gslab.blog_Application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {
    private int id;
    // @NotNull
    @NotEmpty
    @Size(min = 5,message = "username must be contain 4 characters !!")
    private String name;

     @Email(message = "Email address is not valid !! ")
    private String email;

    @NotEmpty
    @Size(min = 3,max = 10,message = "password must contain 10 characters")
    private String password;

     @NotEmpty
    private String about;
}
