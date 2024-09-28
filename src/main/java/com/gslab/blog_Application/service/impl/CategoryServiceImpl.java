package com.gslab.blog_Application.service.impl;

import com.gslab.blog_Application.dao.CategoryRepo;
import com.gslab.blog_Application.dto.CategoryRequest;
import com.gslab.blog_Application.dto.CategoryResponse;
import com.gslab.blog_Application.entity.Category;
import com.gslab.blog_Application.exception.CategoryException;
import com.gslab.blog_Application.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo repo;
    @Override
    public CategoryRequest createCategory(CategoryRequest categoryRequest) {
        Category category= reqToCat(categoryRequest);
        Category saveCategory=repo.save(category);
        return catToReq(saveCategory);
    }

    @Override
    public CategoryRequest updateCategory(CategoryRequest categoryRequest, Integer id) {

        Category category=repo.findById(id).orElseThrow(()->new CategoryException("category is not available"));

        category.setCategoryId(categoryRequest.getCategoryId());
        category.setCategoryTitle(categoryRequest.getCategoryTitle());
        category.setCategoryDescription(category.getCategoryDescription());

         Category saveChanges= repo.save(category);

        return catToReq(saveChanges);
    }

    @Override
    public CategoryResponse getCategoryById(Integer id) {
        Category category=  repo.findById(id).orElseThrow(()->new CategoryException("category is not available"));

        return catToRes(category);
    }

    @Override
    public Integer deleteCategory(Integer id) {
        Category category=  repo.findById(id).orElseThrow(()->new CategoryException("category is not available"));
        repo.delete(category);
        return id;
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        List<CategoryResponse> responses=new ArrayList<>();
        List<Category > categories=repo.findAll();

        //now we have to iterate
        for(Category category : categories){
           CategoryResponse response= catToRes(category);
            responses.add(response);
        }
        return responses;
    }

    private Category reqToCat(CategoryRequest categoryRequest){
        Category category=new ModelMapper().map(categoryRequest,Category.class);
        return category;
    }
    private CategoryRequest catToReq(Category category){
        CategoryRequest categoryRequest=new ModelMapper().map(category,CategoryRequest.class);
        return categoryRequest;
    }
    private CategoryResponse catToRes(Category category){
        CategoryResponse categoryResponse=new ModelMapper().map(category,CategoryResponse.class);
        return categoryResponse;

    }

}
