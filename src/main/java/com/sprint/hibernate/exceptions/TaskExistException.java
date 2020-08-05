package com.sprint.hibernate.exceptions;

public class TaskExistException extends Exception {
    public TaskExistException() { }

    public TaskExistException(String message) {
        super(message);
    }
}
