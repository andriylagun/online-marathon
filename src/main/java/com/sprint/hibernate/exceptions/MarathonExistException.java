package com.sprint.hibernate.exceptions;

public class MarathonExistException extends RuntimeException {
    public MarathonExistException(String message) {
        super(message);
    }
    public MarathonExistException() { }
}

