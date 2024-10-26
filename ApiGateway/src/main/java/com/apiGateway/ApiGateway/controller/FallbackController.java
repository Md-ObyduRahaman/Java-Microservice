package com.apiGateway.ApiGateway.controller;

import com.apiGateway.ApiGateway.entity.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/studentManagementFallback")
public class FallbackController {

    @GetMapping
    public ResponseEntity<BaseResponse<Void>> studentManagementFallback() {
        // Creating a custom response when the student management service is unavailable
        BaseResponse<Void> response = new BaseResponse<>(
                new Date(),
                "Student Management Service is currently unavailable. Please try again later.",
                null,
                HttpStatus.SERVICE_UNAVAILABLE,
                "/student/**"
        );

        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }
}

