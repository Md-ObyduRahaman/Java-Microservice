package com.studentManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiBaseResponse<T> {
    private String timestamp;
    private String message;
    private T data;
    private Integer statusCode;
    private String path; // New field for tracking request path

    // Constructor with path
    public ApiBaseResponse(String message, T data, Integer statusCode, String path) {
        this.timestamp = String.valueOf(new Date()); // Automatically set timestamp
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
        this.path = path; // Set path
    }


}

