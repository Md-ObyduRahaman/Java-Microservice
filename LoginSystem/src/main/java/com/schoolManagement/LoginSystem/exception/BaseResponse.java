package com.schoolManagement.LoginSystem.exception;



import java.util.Date;

public class BaseResponse<T> {
    private Date timestamp;
    private String message;
    private T data;
    private int statusCode;

    public BaseResponse(String message, T data, int statusCode) {
        this.timestamp = new Date(); // Automatically set timestamp
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
    }

    // Getters and setters
    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
