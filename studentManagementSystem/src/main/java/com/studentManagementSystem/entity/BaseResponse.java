package com.studentManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.*;
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
    private String path;

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

