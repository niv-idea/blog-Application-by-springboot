package com.gslab.blog_Application.exception;

import com.gslab.blog_Application.dto.ResponseWrapper;
import com.gslab.blog_Application.enums.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobleExceptionHandler {
    //if the exception occur then it will execute
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ResponseWrapper> userExceptionHandler(UserException exception){
        ResponseWrapper responseWrapper = new ResponseWrapper(Status.FAIL, exception.getMessage());
        return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
    }

    //let's create one more method which handle exception globally
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Map<String , String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
    Map<String,String> map=new HashMap<>();
    exception.getBindingResult().getAllErrors().forEach((error)->{
        String fieldName=((FieldError)error).getField();//if not work then provide the typecasting
        String message=error.getDefaultMessage();
        map.put(fieldName,message);
    });
     return new ResponseEntity<Map<String ,String>>(map,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<ResponseWrapper> categoryExceptionHandler(CategoryException ex){
        ResponseWrapper responseWrapper=new ResponseWrapper(Status.FAIL,ex.getMessage());
        return new ResponseEntity<>(responseWrapper,HttpStatus.NOT_FOUND);

    }
}
