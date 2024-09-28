package com.gslab.blog_Application.service.impl;

import com.gslab.blog_Application.dao.UserRepository;
import com.gslab.blog_Application.dto.UpdateUserRequest;
import com.gslab.blog_Application.dto.UserRequest;
import com.gslab.blog_Application.dto.UserResponse;
import com.gslab.blog_Application.entity.User;
import com.gslab.blog_Application.exception.UserException;
import com.gslab.blog_Application.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserRequest addUser(UserRequest userRequest) {
        User user=userReqToUser(userRequest);
        User userDetails=repository.save(user);//repository always interact with the database to store the data of bean class into the database
        return userToUserReq(userDetails);//and thats why we convert into bean class and then return data into the dto
    }

    @Override
    public UpdateUserRequest updateUser(UpdateUserRequest userRequest, Integer id) {
        //next time you have to add some extra concepts like exception
        User user= repository.findById(id).orElseThrow(()->new UserException("user not found"));
        if(userRequest.getName()!=null){
            user.setName(userRequest.getName());
        }
        if(userRequest.getEmail()!=null){
            user.setEmail(userRequest.getEmail());
        }
        if(userRequest.getPassword()!=null){
            user.setPassword(userRequest.getPassword());
        }
        if(userRequest.getAbout()!=null){
            user.setAbout(userRequest.getAbout());
        }

        User userDetails=repository.save(user);
        return  userToUpdateUserRequest(userDetails);
    }

    @Override
    public UserResponse getUserById(Integer id) {
          User user=repository.findById(id).orElseThrow(()->new UserException("user is not available with this id"));
        return userToResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        //List<UserResponse> responses=new ArrayList<>();
        List<User> users=repository.findAll();
        //now we have to iterate this all users
//        for(User user : users){
//            UserResponse response=userToResponse(user);
//            responses.add(response);
//
//        }
//        return responses;
        List<UserResponse> responses=users.stream().map(this::userToResponse).collect(Collectors.toList());
        return responses;
    }

    @Override
    public Integer deleteUser(Integer id) {
        User user=repository.findById(id).orElseThrow(()->new UserException("this particular user is not exist"));
        repository.delete(user);
        return user.getId();
    }
    private User userReqToUser(UserRequest userRequest){
        User user=new User();
        user.setId(userRequest.getId());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setAbout(userRequest.getAbout());

        return user;

    }
    private UpdateUserRequest userToUpdateUserRequest(User user){
        UpdateUserRequest userRequest=new ModelMapper().map(user,UpdateUserRequest.class);
        return userRequest;

    }
    private UserRequest userToUserReq(User user){
      //  UserRequest userRequest=new UserRequest();
//        userRequest.setId(user.getId());
//        userRequest.setName(user.getName());
//        userRequest.setEmail(user.getEmail());
//        userRequest.setPassword(user.getPassword());
//        userRequest.setAbout(user.getAbout());
        UserRequest userRequest=new ModelMapper().map(user, UserRequest.class);

        return userRequest;
    }
    private UserResponse userToResponse(User user){

        UserResponse userResponse=new ModelMapper().map(user, UserResponse.class);
//        UserResponse userResponse=new UserResponse();
//        userResponse.setId(user.getId());
//        userResponse.setName(user.getName());
//        userResponse.setEmail(user.getEmail());
//        userResponse.setPassword(user.getPassword());
//        userResponse.setAbout(user.getAbout());
        return userResponse;
    }
}
