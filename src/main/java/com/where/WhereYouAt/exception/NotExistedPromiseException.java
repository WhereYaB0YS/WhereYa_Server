package com.where.WhereYouAt.exception;

public class NotExistedPromiseException extends RuntimeException{

    private static final String MESSAGE ="약속을 추가해 주세요";
    public NotExistedPromiseException(){
        super(MESSAGE);
    }

}