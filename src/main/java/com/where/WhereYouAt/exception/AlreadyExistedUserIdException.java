package com.where.WhereYouAt.exception;

public class AlreadyExistedUserIdException extends RuntimeException{

    private static final String MESSAGE ="이미 존재하는 아이디입니다";
    public AlreadyExistedUserIdException(){
        super(MESSAGE);
    }

}
