package com.gslab.blog_Application.dao;

import com.gslab.blog_Application.entity.Category;
import com.gslab.blog_Application.entity.Post;
import com.gslab.blog_Application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    //here we have to create the method for searching by title of post
    List<Post>  searchByTitle(@Param("key") String title);
}
