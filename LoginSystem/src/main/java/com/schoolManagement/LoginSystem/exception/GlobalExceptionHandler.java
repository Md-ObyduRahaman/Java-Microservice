package com.schoolManagement.LoginSystem.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<BaseResponse<Void>> handleAuthException(AuthException ex, WebRequest request) {


        // Create a response with the message, null data, status code, and the request path
        BaseResponse<Void> response = new BaseResponse<>(
                ex.getMessage(),
                null,
                HttpStatus.UNAUTHORIZED,  // 401 status code
                request.getDescription(false)                      // Include the request path
        );

        // Return the response with the status code
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        // Create BaseResponse for error response
        BaseResponse<Void> response = new BaseResponse<>(
                ex.getMessage(),
                null,
                HttpStatus.NOT_FOUND,
                request.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleGlobalException(Exception ex, WebRequest request) {


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