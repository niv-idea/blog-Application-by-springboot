package com.gslab.blog_Application.dto;

import com.gslab.blog_Application.entity.Category;
import com.gslab.blog_Application.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PostRequest {
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private Category category;
    private User user;
    //image and date field comes automatically so, no need to take manually
}
