package com.sprint.hibernate.exceptions;

public class TaskExistException extends RuntimeException {
    public TaskExistException() { }

    public TaskExistException(String message) {
        super(message);
    }
}
