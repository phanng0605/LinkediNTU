package com.example.app.exceptions;

public class FactoryException extends ModelException {

    public FactoryException() {
        super();
    }

    public FactoryException(String message) {
        super(message);
    }

    public FactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public FactoryException(Throwable cause) {
        super(cause);
    }

}