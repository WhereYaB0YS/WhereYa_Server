package com.where.WhereYouAt.exception;

public class AlreadyExistedFriendException extends RuntimeException{
    private static final String MESSAGE ="이미 등록된 친구입니다";
    public AlreadyExistedFriendException(){
        super(MESSAGE);
    }
}
