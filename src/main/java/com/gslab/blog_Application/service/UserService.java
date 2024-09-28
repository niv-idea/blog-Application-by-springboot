package com.gslab.blog_Application.service;

import com.gslab.blog_Application.dto.UpdateUserRequest;
import com.gslab.blog_Application.dto.UserRequest;
import com.gslab.blog_Application.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    UserRequest addUser(UserRequest userRequest);

    UpdateUserRequest updateUser(UpdateUserRequest updateUserRequest, Integer id);

    UserResponse getUserById(Integer id);

    List<UserResponse> getAllUsers();

    Integer deleteUser(Integer id);
}
