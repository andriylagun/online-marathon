package com.sprint.hibernate.exceptions;

public class EmailExistException extends RuntimeException {
    public EmailExistException() {
    }

    public EmailExistException(String message) {
        super(message);
    }
}
