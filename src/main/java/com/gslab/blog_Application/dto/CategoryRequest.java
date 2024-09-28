package com.gslab.blog_Application.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryRequest {

    private int categoryId;

    @NotBlank
    @Size(min = 4)
    private String categoryTitle;

    @NotEmpty
    @Size(max = 10)
    private String categoryDescription;
}
