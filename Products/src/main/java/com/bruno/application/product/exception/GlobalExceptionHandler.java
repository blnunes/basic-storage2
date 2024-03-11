package com.bruno.application.product.exception;

import com.bruno.application.product.dto.ErrorRS;
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
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorRS> handleDataIntegrityViolation(final DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof ConstraintViolationException constraintViolationException) {
            if (constraintViolationException.getMessage().contains("Unique index or primary key violation")) {
                return ResponseEntity.badRequest().body(
                        new ErrorRS(Collections.singletonList("The product already exists in the database."),
                        HttpStatus.INTERNAL_SERVER_ERROR.value()));
            }
        }
        return ResponseEntity.badRequest().body(
                new ErrorRS(Collections.singletonList(ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorRS> handleMethodArgumentNotVali(final MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(new ErrorRS(
                ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList(),
                HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorRS> handleDataIntegrityViolation(final NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                new ErrorRS(Collections.singletonList(ex.getMessage()),
                HttpStatus.NOT_FOUND.value()));
    }
}
