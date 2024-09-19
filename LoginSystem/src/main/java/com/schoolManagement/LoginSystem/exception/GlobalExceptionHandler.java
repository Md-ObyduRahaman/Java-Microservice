package com.schoolManagement.LoginSystem.exception;




import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<BaseResponse<Void>> handleAuthException(AuthException ex) {
        BaseResponse<Void> response = new BaseResponse<>(
                ex.getMessage(),
                null,
                HttpStatus.UNAUTHORIZED.value() // 401 status code
        );

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleSecurityException(Exception ex) throws JsonProcessingException {


        BaseResponse<Void> baseResponse;
        if (ex instanceof UsernameNotFoundException) {
            baseResponse = new BaseResponse<>("Username not found: " + ex.getMessage(), null, 403);
        } else if (ex instanceof AccessDeniedException) {
            baseResponse = new BaseResponse<>("Access denied: " + ex.getMessage(), null, 403);
        } else if (ex instanceof MalformedJwtException) {
            baseResponse = new BaseResponse<>("JWT signature is tampered with! Please provide a valid token.", null, 403);
        } else if (ex instanceof SignatureException) {
            baseResponse = new BaseResponse<>("Invalid JWT signature! Please provide a valid token.", null, 403);
        } else if (ex instanceof ExpiredJwtException) {
            baseResponse = new BaseResponse<>("JWT token has expired!", null, 403);
        } else if (ex instanceof InsufficientAuthenticationException) {
            baseResponse = new BaseResponse<>("Please provide a token in the Authorization header!", null, 403);
        } else if (ex.getMessage() != null) {
            baseResponse = new BaseResponse<>("Internal server error! Check back-end code.", null, 500);
        } else {
            baseResponse = new BaseResponse<>("Unknown error occurred.", null, 500);
        }



        return new ResponseEntity<>(baseResponse, HttpStatus.UNAUTHORIZED);
    }*/

    // Handle custom exception (e.g., ResourceNotFoundException)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                ex.getMessage(),
                request.getDescription(false),
                ex.getClass().getSimpleName(),  // Exception class name
                HttpStatus.NOT_FOUND.value()    // Status code 404
        );

        // Create BaseResponse for error response
        BaseResponse<Void> response = new BaseResponse<>(
                errorDetails.getMessage(),
                null,
                HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                ex.getMessage(),
                request.getDescription(false),
                ex.getClass().getSimpleName(),  // Exception class name
                HttpStatus.INTERNAL_SERVER_ERROR.value() // Status code 500
        );

        // Create BaseResponse for error response
        BaseResponse<Void> response = new BaseResponse<>(
                errorDetails.getMessage(),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
