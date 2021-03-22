package com.where.WhereYouAt.exception;

public class NotExistedFriendException extends RuntimeException{
    private static final String MESSAGE ="존재하지 않는 친구입니다";
    public NotExistedFriendException(){
        super(MESSAGE);
    }
}
