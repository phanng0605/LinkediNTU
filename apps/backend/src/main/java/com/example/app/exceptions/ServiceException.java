package com.example.app.exceptions;

public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
        System.err.println("Service Exception: " + message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        System.err.println("Service Exception: " + message);
        System.err.println("Service Exception Cause: " + cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
        System.err.println("Service Exception Cause: " + cause);
    }

    public ServiceException() {
        super();
        System.err.println("Service Exception");
    }
}
