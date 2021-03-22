package com.where.WhereYouAt.message;


import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Data
@Getter
public class ResponseMessage {

    private int code;
    private String message;
    //private Object object;

    public ResponseMessage(HttpStatus httpStatus, String message){
        this.code = httpStatus.value();
        this.message = message;
    }
}
