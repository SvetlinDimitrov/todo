package com.internship.todo.web;

import com.internship.todo.feature.user.dto.UserPostRequest;
import com.internship.todo.feature.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserService service;

  @PostMapping
  public ResponseEntity<?> createUser(@Valid @RequestBody UserPostRequest createUserDto) {
    service.createUser(createUserDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
