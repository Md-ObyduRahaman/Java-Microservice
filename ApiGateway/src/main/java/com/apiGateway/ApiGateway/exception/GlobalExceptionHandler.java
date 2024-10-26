package com.apiGateway.ApiGateway.exception;



import com.apiGateway.ApiGateway.entity. ;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity< <Void>> handleGlobalException(Exception ex, WebRequest request) {

        ex.printStackTrace();

        // Create   for error response
         <Void> response = new  <>(
                ex.getMessage(),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
