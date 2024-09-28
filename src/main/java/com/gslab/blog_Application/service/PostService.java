package com.gslab.blog_Application.service;

import com.gslab.blog_Application.dto.PostRequest;
import com.gslab.blog_Application.dto.PostResponse;
import com.gslab.blog_Application.dto.PostResponse1;

import java.util.List;

public interface PostService {
    //create post
    PostRequest createPost(PostRequest postRequest,Integer userId,Integer categoryId);

    //update post
    PostRequest updatePost(PostRequest postRequest,Integer postId);

    //delete post
    void deletePost(Integer postId);

    //getPost
    PostResponse getPostById(Integer postId);

    //getAll post
    PostResponse1 getAll(Integer size, Integer page, String sortBy, String sortDir);

    //get posts by user
    List<PostResponse> getPostByUserId(Integer userId);

    //get posts by category
    List<PostResponse> getPostByCategoryId(Integer categoryId);

   List<PostResponse> searchPosts(String keyword);

}
