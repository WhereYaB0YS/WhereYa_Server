package com.where.WhereYouAt.exception;

public class NotExistedAppointmentException extends RuntimeException{
    private static final String MESSAGE ="존재하지 않는 약속입니다";
    public NotExistedAppointmentException(){
        super(MESSAGE);
    }
}
