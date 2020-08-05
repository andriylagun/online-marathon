package com.sprint.hibernate.exceptions;

public class MarathonExistException extends Exception {
    public MarathonExistException() { }

    public MarathonExistException(String message) {
        super(message);
    }
}
