package com.internship.todo.web;

import com.internship.todo.infrastructure.shared.exceptions.BadResponseException;
import com.internship.todo.infrastructure.shared.exceptions.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviser {

  @ExceptionHandler(BadResponseException.class)
  public ResponseEntity<Exception> handleBadResponseException(BadResponseException e) {
    return new ResponseEntity<>(new Exception(e.getMessage()), HttpStatus.BAD_REQUEST);
  }
}