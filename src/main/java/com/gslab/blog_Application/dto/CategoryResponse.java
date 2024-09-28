package com.gslab.blog_Application.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryResponse {

    private int categoryId;

    private String categoryTitle;

    private String categoryDescription;
}
