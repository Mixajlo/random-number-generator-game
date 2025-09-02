package com.xessable.interview.api.handlers;

import com.xessable.interview.api.dto.ErrorResponse;
import com.xessable.interview.api.exceptions.NoGameFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, NoGameFoundException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception ex) {
        String msg = ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(msg));
    }
}
