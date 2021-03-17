package com.where.WhereYouAt.exception;

public class InCorrectUserException extends RuntimeException{
    private static final String MESSAGE ="올바르지 않은 사용자 입니다";
    public InCorrectUserException(){
        super(MESSAGE);
    }
}
