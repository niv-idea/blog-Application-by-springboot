package com.gslab.blog_Application.dto;

import lombok.Data;

@Data
public class UserResponse {
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}
