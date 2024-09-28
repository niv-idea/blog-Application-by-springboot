package com.gslab.blog_Application.dao;

import com.gslab.blog_Application.dto.UserResponse;
import com.gslab.blog_Application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer > {

    Optional<User> findByEmail(String email);

}
