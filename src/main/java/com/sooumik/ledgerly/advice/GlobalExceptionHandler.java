package com.sooumik.ledgerly.advice;

import com.sooumik.ledgerly.exceptions.DuplicateResourceFoundException;
import com.sooumik.ledgerly.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Handling ResourceNotFound Exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException ex) {

        ApiError error = ApiError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .subErrors(Collections.emptyList())
                .build();

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message("Resource not found")
                .error(error)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    //Handling DuplicateResourceFoundException Exception
    @ExceptionHandler(DuplicateResourceFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateResourceException(DuplicateResourceFoundException ex) {

        ApiError error = ApiError.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .subErrors(Collections.emptyList())
                .build();

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message("Duplicate resource found")
                .error(error)
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(
            MethodArgumentNotValidException ex) {

        List<String> subErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        ApiError error = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .subErrors(subErrors)
                .build();

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message("Validation failed")
                .error(error)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            Exception ex) {

        ApiError error = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .subErrors(Collections.emptyList())
                .build();

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message("Something went wrong")
                .error(error)
                .build();

        return ResponseEntity.internalServerError().body(response);
    }
}
