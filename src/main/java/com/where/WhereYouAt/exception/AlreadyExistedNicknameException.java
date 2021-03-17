package com.where.WhereYouAt.exception;

public class AlreadyExistedNicknameException extends RuntimeException{
    private static final String MESSAGE ="이미 존재하는 닉네임입니다";
    public AlreadyExistedNicknameException(){
        super(MESSAGE);
    }
}
