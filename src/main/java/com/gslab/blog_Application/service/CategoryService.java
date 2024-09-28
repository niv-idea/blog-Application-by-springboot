package com.gslab.blog_Application.service;

import com.gslab.blog_Application.dto.CategoryRequest;
import com.gslab.blog_Application.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {


     CategoryRequest createCategory(CategoryRequest categoryRequest);

     CategoryRequest updateCategory(CategoryRequest categoryRequest,Integer id);

     CategoryResponse getCategoryById(Integer id);

     Integer deleteCategory(Integer id);

     List<CategoryResponse> getAllCategory();

}
