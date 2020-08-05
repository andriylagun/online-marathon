package com.sprint.hibernate.exceptions;

public class MarathonNotFoundByIDException extends RuntimeException {
    public MarathonNotFoundByIDException() {
    }

    public MarathonNotFoundByIDException(String message) {
        super(message);
    }
}
