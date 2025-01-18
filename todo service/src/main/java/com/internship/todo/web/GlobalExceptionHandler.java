package com.internship.todo.web;

import com.internship.todo.infrastructure.shared.exceptions.ProjectNotFoundException;
import com.internship.todo.infrastructure.shared.exceptions.TaskNotFoundException;
import com.internship.todo.infrastructure.shared.exceptions.UserAlreadyExistsException;
import com.internship.todo.infrastructure.shared.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ProjectNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleBadResponseException(ProjectNotFoundException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    problemDetail.setTitle("Project Not Found");
    problemDetail.setType(URI.create("http://localhost:8080/errors/project-not-found"));

    return new ResponseEntity<>(problemDetail, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleBadResponseException(UserNotFoundException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    problemDetail.setTitle("User Not Found");
    problemDetail.setType(URI.create("http://localhost:8080/errors/user-not-found"));

    return new ResponseEntity<>(problemDetail, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(TaskNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleBadResponseException(TaskNotFoundException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    problemDetail.setTitle("Task Not Found");
    problemDetail.setType(URI.create("http://localhost:8080/errors/task-not-found"));

    return new ResponseEntity<>(problemDetail, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ProblemDetail> handleBadResponseException(UserAlreadyExistsException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    problemDetail.setTitle("User Already Exists");
    problemDetail.setType(URI.create("http://localhost:8080/errors/user-already-exists"));

    return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ProblemDetail> handleValidationExceptions(MethodArgumentNotValidException ex) {
    String detail = ex.getBindingResult().getFieldErrors().stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .reduce((acc, error) -> acc + "; " + error)
        .orElse("Validation error");

    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
    problemDetail.setTitle("Validation Error");
    problemDetail.setType(URI.create("http://localhost:8080/errors/validation-error"));

    return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
  }
}