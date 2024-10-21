package com.studentManagementSystem.exception;

import com.studentManagementSystem.entity.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse<Void>> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request)
    {
        // Create BaseResponse for error response
        BaseResponse<Void> response = new BaseResponse<>(
                ex.getMessage(),
                null,
                HttpStatus.NOT_FOUND,
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleGlobalException(Exception ex, WebRequest request) {

        ex.printStackTrace();

        // Create BaseResponse for error response
        BaseResponse<Void> response = new BaseResponse<>(
                ex.getMessage(),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
