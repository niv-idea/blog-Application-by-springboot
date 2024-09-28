package com.gslab.blog_Application.controller;

import com.gslab.blog_Application.dto.ResponseWrapper;
import com.gslab.blog_Application.dto.UpdateUserRequest;
import com.gslab.blog_Application.dto.UserRequest;
import com.gslab.blog_Application.dto.UserResponse;
import com.gslab.blog_Application.enums.Status;
import com.gslab.blog_Application.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//it is endpoints of our backend application

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;
    //actual this is happening
   // UserService service=new UserController();

    @PostMapping("/addUser")
    public ResponseEntity<UserRequest> addUser(@Valid @RequestBody UserRequest userRequest){
       UserRequest userRequest1= service.addUser(userRequest);
        return new ResponseEntity<>(userRequest1,HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<ResponseWrapper> updateUser(@Valid @RequestBody UserRequest userRequest ,@PathVariable(value = "id") Integer id){
        UpdateUserRequest updateUser= service.updateUser(new UpdateUserRequest(),id);
        return new ResponseEntity<>(new ResponseWrapper(Status.SUCCESS,updateUser),HttpStatus.OK);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ResponseWrapper> deleteUser(@PathVariable(value = "id") int id){
        Integer userId=service.deleteUser(id);
        ResponseWrapper responseWrapper=new ResponseWrapper(Status.SUCCESS,userId);

        return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseWrapper> getAllUsers(){
      List<UserResponse> userResponses=service.getAllUsers();
        ResponseWrapper responseWrapper=new ResponseWrapper(Status.SUCCESS,userResponses);
        return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
    }
    @GetMapping("/{id}/get")
    public ResponseEntity<ResponseWrapper> getUserById(@PathVariable(value = "id") Integer id){
        UserResponse userResponse=service.getUserById(id);
        ResponseWrapper responseWrapper=new ResponseWrapper(Status.SUCCESS,userResponse);
        return new ResponseEntity<>(responseWrapper,HttpStatus.OK);
    }

}
