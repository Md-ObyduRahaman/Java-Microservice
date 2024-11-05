package com.studentManagementSystem.exception;


public class ParentServiceException extends RuntimeException {
    private String errorCode;
    private String errorMessage;
    private String apiPath;
    private String errorTime;

    public ParentServiceException(String errorCode, String errorMessage, String apiPath, String errorTime) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.apiPath = apiPath;
        this.errorTime = errorTime;
    }

    // Getters and custom methods if needed

    @Override
    public String toString() {
        return String.format("Error Code: %s, Message: %s, Path: %s, Time: %s", errorCode, errorMessage, apiPath, errorTime);
    }
}

