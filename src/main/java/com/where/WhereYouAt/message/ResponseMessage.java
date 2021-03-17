package com.where.WhereYouAt.message;


import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Data
@Getter
public class ResponseMessage {

    private int code;
    private String message;
    private Object object;

    public ResponseMessage(HttpStatus httpStatus, String message){
        this.code = httpStatus.value();
        this.message = message;
    }

    public ResponseMessage(HttpStatus httpStatus, String message,Object object){
        this.code = httpStatus.value();
        this.message = message;
        this.object=object;
    }
}
