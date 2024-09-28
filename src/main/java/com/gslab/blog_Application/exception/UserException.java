package com.gslab.blog_Application.exception;

public class UserException extends RuntimeException{
    String message;
    Object obj;
    public UserException(String message) {
        super(message);
    }
     public UserException(String message, Object obj){
       this.message=message;
       this.obj=obj;
    }
}
