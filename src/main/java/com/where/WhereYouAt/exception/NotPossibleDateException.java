package com.where.WhereYouAt.exception;

public class NotPossibleDateException extends RuntimeException{

    private static final String MESSAGE ="이미 지난 날짜입니다";
    public NotPossibleDateException(){
        super(MESSAGE);
    }

}
