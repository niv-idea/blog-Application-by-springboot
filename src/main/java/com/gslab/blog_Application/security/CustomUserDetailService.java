package com.gslab.blog_Application.security;

import com.gslab.blog_Application.dao.UserRepository;
import com.gslab.blog_Application.entity.User;
import com.gslab.blog_Application.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//whenever spring security needs to call or laod user by username that method will, can call here
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //loading user from database  by username
     User user = repository.findByEmail(username).orElseThrow(()->new UserException("user not found",username));
        return user;
    }
}
