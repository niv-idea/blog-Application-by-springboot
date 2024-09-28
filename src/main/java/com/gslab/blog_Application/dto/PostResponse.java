package com.gslab.blog_Application.dto;

import com.gslab.blog_Application.entity.Category;
import com.gslab.blog_Application.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class PostResponse {
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private User user;
    private Category category;

}
