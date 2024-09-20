package com.schoolManagement.LoginSystem.exception;

import java.util.Date;

public class BaseResponse<T> {
    private Date timestamp;
    private String message;
    private T data;
    private int statusCode;
    private String path; // New field for tracking request path

    // Constructor with path
    public BaseResponse(String message, T data, int statusCode, String path) {
        this.timestamp = new Date(); // Automatically set timestamp
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
        this.path = path; // Set path
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
