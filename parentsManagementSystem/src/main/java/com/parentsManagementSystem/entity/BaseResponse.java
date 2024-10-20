package com.parentsManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private Date timestamp;
    private String message;
    private T data;
    private HttpStatus statusCode;
    private String path; // New field for tracking request path

    // Constructor with path
    public BaseResponse(String message, T data, HttpStatus statusCode, String path) {
        this.timestamp = new Date(); // Automatically set timestamp
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
        this.path = path; // Set path
    }


    public int getStatusCode() {
        return statusCode.value();
    }
}

