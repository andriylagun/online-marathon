package com.sprint.hibernate.exceptions;

public class AddUserToMarathonException extends RuntimeException{
    public AddUserToMarathonException() {
    }

    public AddUserToMarathonException(String message) {
        super(message);
    }
}
