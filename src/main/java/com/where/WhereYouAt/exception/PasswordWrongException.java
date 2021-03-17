package com.where.WhereYouAt.exception;

public class PasswordWrongException extends RuntimeException{

    private static final String MESSAGE ="비밀번호가 틀렸습니다";
    public PasswordWrongException(){
        super(MESSAGE);
    }

}

