package com.gslab.blog_Application.controller;

import com.gslab.blog_Application.dto.CategoryRequest;
import com.gslab.blog_Application.dto.CategoryResponse;
import com.gslab.blog_Application.dto.ResponseWrapper;
import com.gslab.blog_Application.enums.Status;
import com.gslab.blog_Application.service.CategoryService;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/apis/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping("/add")
    public ResponseEntity<ResponseWrapper> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        CategoryRequest saveCategory=service.createCategory(categoryRequest);
        ResponseWrapper responseWrapper=new ResponseWrapper(Status.SUCCESS,saveCategory);
        return new ResponseEntity<>(responseWrapper, HttpStatus.CREATED);
    }
    @PostMapping("/{id}/update")
    public ResponseEntity<ResponseWrapper> updateCategory(@Valid @RequestBody CategoryRequest categoryRequest,@PathVariable("id")Integer id){
        CategoryRequest saveCategory=service.updateCategory(categoryRequest, id);
        ResponseWrapper responseWrapper=new ResponseWrapper(Status.SUCCESS,saveCategory);
        return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    }
    @GetMapping("/{id}/get")
    public ResponseEntity<ResponseWrapper> getCategoryById(@PathVariable Integer id){
        CategoryResponse response=service.getCategoryById(id);
        ResponseWrapper responseWrapper=new ResponseWrapper(Status.SUCCESS,response);
        return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseWrapper> getAllCategory(){
       List<CategoryResponse> responses=service.getAllCategory();
        ResponseWrapper responseWrapper=new ResponseWrapper(Status.SUCCESS,responses);
        return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    }
    @DeleteMapping("/{catId}/delete")
    public ResponseEntity<ResponseWrapper> deleteCategoryById(@PathVariable Integer catId){
       Integer categoryID=service.deleteCategory(catId);
        ResponseWrapper responseWrapper=new ResponseWrapper(Status.SUCCESS,categoryID);
        return new ResponseEntity<>(responseWrapper, HttpStatus.OK);

    }

}
