package com.gslab.blog_Application.dao;


import com.gslab.blog_Application.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer > {

}
