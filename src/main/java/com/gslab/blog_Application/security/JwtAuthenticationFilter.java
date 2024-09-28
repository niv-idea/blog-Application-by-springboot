package com.gslab.blog_Application.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailService userDetailService;
    @Autowired
    private JwtTokenHelper tokenHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {



        //get token
        String requestToken=request.getHeader("Authorization");
    //Bearer 2345t85vbbvrvgcsdgcdsjhvdjhv  like this

        //we can print token
        System.out.println(requestToken);
       String username=null;
       //we are trying to get actual token which start after the bearer word
       String token=null;

       if(requestToken!=null && requestToken.startsWith("Bearer")){
           token=requestToken.substring(7);


           try{
               username=tokenHelper.getUsernameFromToken(token);
           }catch (IllegalArgumentException e){
               System.out.println("unable to get jwt token");
           }catch (ExpiredJwtException e){
               System.out.println("jwt token has expire ");
           }catch (MalformedJwtException e){
               System.out.println("invalid jwt ");
           }



       }else {
           System.out.println("jwt token does not begin with Bearer ");
       }


      //once we get the token , now validate
        if(username!=null && SecurityContextHolder.getContext().getAuthentication() ==null){
            UserDetails userDetails=userDetailService.loadUserByUsername(username);

            if(tokenHelper.validateToken(token,userDetails)){
                //means everything is okay
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(token,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


            }else {
                System.out.println("invalid jwt token");
            }

        }else {
            System.out.println("username is null or context is not null");
        }


    filterChain.doFilter(request, response);



    }
}
