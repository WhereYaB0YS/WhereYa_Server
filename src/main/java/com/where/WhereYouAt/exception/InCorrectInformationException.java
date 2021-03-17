package com.where.WhereYouAt.exception;

public class InCorrectInformationException extends RuntimeException{
    private static final String MESSAGE ="입력하신 정보가 일치하지 않습니다";
    public InCorrectInformationException(){
        super(MESSAGE);
    }
}
