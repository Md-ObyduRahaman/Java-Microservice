package com.schoolManagement.LoginSystem.exception;



import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
    private String exception;
    private int statusCode;

    public ErrorDetails(Date timestamp, String message, String details, String exception, int statusCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.exception = exception;
        this.statusCode = statusCode;
    }

    // Getters
    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public String getException() {
        return exception;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
