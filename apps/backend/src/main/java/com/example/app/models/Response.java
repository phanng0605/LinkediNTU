package com.example.app.models;

import com.example.app.exceptions.ModelException;

public class Response<T> {

    public enum ResponseStatus {
        SUCCESS, ERROR
    }

    private ResponseStatus status;
    private String message;
    private T data;

    public Response() {
        this.status = ResponseStatus.SUCCESS;
        this.message = "";
    }

    public Response(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(String status, String message) throws ModelException {
        try {
            this.status = ResponseStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new ModelException("Invalid status");
        }
        this.message = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Response<T> setStatus(ResponseStatus status) {
        this.status = status;
        return this;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }

}
