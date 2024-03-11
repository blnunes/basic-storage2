package com.bruno.application.product.exception;

import com.bruno.application.product.dto.ErrorResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(final DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof ConstraintViolationException constraintViolationException) {
            if (constraintViolationException.getMessage().contains("Unique index or primary key violation")) {
                return ResponseEntity.badRequest().body(
                        new ErrorResponse(Collections.singletonList("The product already exists in the database."),
                        HttpStatus.INTERNAL_SERVER_ERROR.value()));
            }
        }
        return ResponseEntity.badRequest().body(
                new ErrorResponse(Collections.singletonList(ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(
                ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList(),
                HttpStatus.BAD_REQUEST.value()));
    }
}
