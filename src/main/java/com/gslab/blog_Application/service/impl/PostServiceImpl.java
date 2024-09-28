package com.gslab.blog_Application.service.impl;

import com.gslab.blog_Application.dao.CategoryRepo;
import com.gslab.blog_Application.dao.PostRepo;
import com.gslab.blog_Application.dao.UserRepository;
import com.gslab.blog_Application.dto.PostRequest;
import com.gslab.blog_Application.dto.PostResponse;

import com.gslab.blog_Application.dto.PostResponse1;
import com.gslab.blog_Application.entity.Category;
import com.gslab.blog_Application.entity.Post;
import com.gslab.blog_Application.entity.User;
import com.gslab.blog_Application.exception.CategoryException;
import com.gslab.blog_Application.exception.PostException;
import com.gslab.blog_Application.exception.UserException;
import com.gslab.blog_Application.service.PostService;
import org.hibernate.boot.model.source.spi.SingularAttributeNature;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;  //for the required changes

    @Override
    public PostRequest createPost(PostRequest postRequest, Integer userId, Integer categoryId) {
        User user=userRepository.findById(userId).orElseThrow(()->new UserException("this type of  user are not available"));
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new CategoryException("sorry this kind of category are not available "));

        Post post=reqToPost(postRequest);
        post.setImageName("default image.ung");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savePost=repository.save(post);
        return postToReq(savePost);
    }

    @Override
    public PostRequest updatePost(PostRequest postRequest, Integer postId) {
        Post post=repository.findById(postId).orElseThrow(()->new PostException("sorry this kind of post is not available"));
        //I want to update only this particular fields of object of the entity class
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setImageName(postRequest.getImageName());
        Post postResult= repository.save(post);

        return postToReq(postResult);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=repository.findById(postId).orElseThrow(()->new PostException("this post is not available "));
        repository.delete(post);
    }

    @Override
    public PostResponse getPostById(Integer postId) {
        Post post=repository.findById(postId).orElseThrow(()->new PostException("this post is not available "));
        return postToResponse(post);
    }

    @Override
    public PostResponse1 getAll(Integer number, Integer size, String sortBy, String sortDir) {
       // List<PostResponse> responses=new ArrayList<>();
    //this is the concept of pagination
        //here we can also use ternary operator
        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();

        }else {
            sort=Sort.by(sortBy).descending();
        }
        Pageable p=PageRequest.of(number,size,sort);
        Page<Post> pagePost=repository.findAll(p);
        List<Post> posts = pagePost.getContent();
        //retrive by using java 7
//        for(Post post: posts){
//           PostResponse response= postToResponse(post);
//           responses.add(response);
//        }
        List<PostResponse> responses=posts.stream().map(this::postToResponse).collect(Collectors.toList());
        PostResponse1 postResponse=new PostResponse1();
        postResponse.setContent(responses);
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        //this will show us that our page is last or not
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public List<PostResponse> getPostByUserId(Integer userId) {
        List<PostResponse> responses=new ArrayList<>();
        User user=userRepository.findById(userId).orElseThrow(()->new UserException("user is not available "));
        List<Post> posts=repository.findByUser(user);
        //now the time is to retrieve
        for(Post post :posts){
          PostResponse response= postToResponse(post);
          responses.add(response);
        }
        return responses;
    }

    @Override
    public List<PostResponse> getPostByCategoryId(Integer categoryId) {
        List<PostResponse> responses=new ArrayList<>();
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new CategoryException("not available"));
        List<Post> posts=repository.findByCategory(category);
        for(Post post :posts){
            PostResponse response=  postToResponse(post);
            responses.add(response);
        }

        return responses;
    }

    @Override
    public List<PostResponse> searchPosts(String keyword) {
        List<Post> posts=repository.searchByTitle("%"+keyword+"%");
        List<PostResponse> responses=posts.stream().map(this::postToResponse).collect(Collectors.toList());
        return responses;
    }

    private PostRequest postToReq(Post post){
        return modelMapper.map(post,PostRequest.class);
    }
    private Post reqToPost(PostRequest postRequest){
        return modelMapper.map(postRequest,Post.class);
    }
    private PostResponse postToResponse(Post post){
        return modelMapper.map(post, PostResponse.class);
    }
    private Post postToResponse(PostResponse postResponse){
        return modelMapper.map(postResponse, Post.class);
    }

}
