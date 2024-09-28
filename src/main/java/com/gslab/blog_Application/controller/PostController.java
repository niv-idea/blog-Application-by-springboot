package com.gslab.blog_Application.controller;

import com.gslab.blog_Application.config.AppConstants;
import com.gslab.blog_Application.dto.PostRequest;
import com.gslab.blog_Application.dto.PostResponse;
import com.gslab.blog_Application.dto.PostResponse1;
import com.gslab.blog_Application.dto.ResponseWrapper;
import com.gslab.blog_Application.entity.Post;
import com.gslab.blog_Application.enums.Status;
import com.gslab.blog_Application.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PostController {

    @Autowired
    private PostService postService;

    //create
    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<ResponseWrapper> createPost(@RequestBody PostRequest postRequest,@PathVariable Integer userId, @PathVariable Integer categoryId){
       PostRequest createdPost= postService.createPost(postRequest,userId,categoryId);
      ResponseWrapper responseWrapper=new ResponseWrapper(Status.SUCCESS,createdPost);
      return new ResponseEntity<>(responseWrapper, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/post/{postId}")
    public ResponseEntity<ResponseWrapper> updatePost(@RequestBody PostRequest postRequest, @PathVariable Integer postId){
       PostRequest updatedData =postService.updatePost(postRequest,postId);
        ResponseWrapper responseWrapper=new ResponseWrapper(Status.SUCCESS,updatedData);
        return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
    }

    //getPostById
    @GetMapping("/post/{postId}")
    public ResponseEntity<ResponseWrapper> getPostById(Integer postId){
       PostResponse postResponse= postService.getPostById(postId);
        ResponseWrapper responseWrapper=new ResponseWrapper(Status.SUCCESS,postResponse);
        return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
    }

    //getAll
    @GetMapping("/getAll")
    //by the help of the pagination we can show that we can post in sizes 1 or more than on
     public ResponseEntity<ResponseWrapper> getAll(
             @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer number,
             @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer size,
             @RequestParam(value = "sortBy",defaultValue =AppConstants.SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir",defaultValue =AppConstants.SORT_DIR,required = false) String sortDir
             ){
        PostResponse1 responses=postService.getAll(number, size, sortBy,sortDir);
         ResponseWrapper responseWrapper=new ResponseWrapper(Status.SUCCESS,responses);
         return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
     }

    //getByUser
    @GetMapping("/user/{userId}")
     public ResponseEntity<ResponseWrapper> getPostByUserId(@PathVariable Integer userId ){
        List<PostResponse> responses=postService.getPostByUserId(userId);
         ResponseWrapper responseWrapper=new ResponseWrapper(Status.SUCCESS,responses);
         return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
     }

    //getByCategory
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ResponseWrapper> getPostByCategoryId(@PathVariable Integer categoryId ){
        List<PostResponse> responses=postService.getPostByCategoryId(categoryId);
        ResponseWrapper responseWrapper=new ResponseWrapper(Status.SUCCESS,responses);
        return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
    }
    //delete post
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ResponseWrapper> deletePost(Integer postId){
        postService.deletePost(postId);
        ResponseWrapper responseWrapper=new ResponseWrapper(Status.SUCCESS,"post successfully deleted");
        return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
    }
    @GetMapping("/post/search/{keywords}")
    public ResponseEntity<ResponseWrapper> searchPosts(@PathVariable(value = "keywords") String keyword){
        List<PostResponse> responses=postService.searchPosts(keyword);
        ResponseWrapper responseWrapper=new ResponseWrapper(Status.SUCCESS,responses);
        return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
    }


















}
