package com.sprint.hibernate.exceptions;

public class SprintExistException extends RuntimeException {
    public SprintExistException() { }

    public SprintExistException(String message) {
        super(message);
    }
}
