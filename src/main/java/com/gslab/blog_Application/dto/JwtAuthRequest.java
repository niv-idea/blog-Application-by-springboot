package com.gslab.blog_Application.dto;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String username;
    private String password;
}
