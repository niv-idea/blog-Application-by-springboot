package com.gslab.blog_Application.dto;

import com.gslab.blog_Application.enums.Status;
import lombok.Data;


@Data
//with the help of this class we can send the final response to user or client

public class ResponseWrapper {
    private Status status;
    private  Object data;
    public ResponseWrapper(Status status, Object data){
        this.status=status;
        this.data=data;
    }



    // is there any need to create the constructor

}
