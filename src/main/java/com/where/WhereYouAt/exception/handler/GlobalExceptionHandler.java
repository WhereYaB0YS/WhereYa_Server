package com.where.WhereYouAt.exception.handler;


import com.where.WhereYouAt.exception.*;
import com.where.WhereYouAt.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistedUserIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleAlreadyExistedEmailException(AlreadyExistedUserIdException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(AlreadyExistedNicknameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleAlreadyExistedNicknameException(AlreadyExistedNicknameException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(PasswordWrongException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlePasswordWrongException(PasswordWrongException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(NotExistedUserIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotExistedEmailException(NotExistedUserIdException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(InCorrectInformationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInCorrectInformationException(InCorrectInformationException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage());
    }



    @ExceptionHandler(InCorrectUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInCorrectUserException(InCorrectUserException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(NotExistedFriendException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotExistedFriendException(NotExistedFriendException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(AlreadyExistedFriendException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleAlreadyExistedFriendException(AlreadyExistedFriendException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(NotExistedAppointmentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotExistedAppointmentException(NotExistedAppointmentException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage());
    }



    //위의 exception handler중에서 가장 적합한 것을 handling하게 되고 만약 세개가 다 아니라면 이쪽으로 handling이 진행 된다.
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleRuntimeException(RuntimeException ex){
        log.error("서버오류: {}",ex.getMessage(),ex);
        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 오류가 발생하였습니다");
    }
}
