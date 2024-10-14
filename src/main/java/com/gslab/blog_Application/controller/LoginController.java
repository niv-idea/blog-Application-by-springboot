package com.gslab.blog_Application.controller;

import com.gslab.blog_Application.dto.JwtAuthRequest;
import com.gslab.blog_Application.dto.JwtAuthResponse;
import com.gslab.blog_Application.security.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class LoginController {
    @Autowired
    private JwtTokenHelper tokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse>  createToken(@RequestBody JwtAuthRequest request){
        authenticate(request.getUsername() ,request.getPassword());
        UserDetails userDetails=userDetailsService.loadUserByUsername(request.getUsername());
       String token= tokenHelper.generateToken(userDetails);

       JwtAuthResponse response=new JwtAuthResponse(token);

       return new ResponseEntity<>(response, HttpStatus.OK);

    }
   private void authenticate(String username, String password){
       UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
       manager.authenticate(authenticationToken);
   }

}
