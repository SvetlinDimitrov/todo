package com.internship.todo.web;

import com.internship.todo.infrastructure.shared.exceptions.BadResponseException;
import com.internship.todo.infrastructure.shared.exceptions.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviser {

  @ExceptionHandler(BadResponseException.class)
  public ResponseEntity<Exception> handleBadResponseException(BadResponseException e) {
    return new ResponseEntity<>(new Exception(e.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage()));

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}