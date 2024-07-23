package com.todo.todo.controller;

import com.todo.todo.exceptions.BadResponseException;
import com.todo.todo.model.views.Exception;
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