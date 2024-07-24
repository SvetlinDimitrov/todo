package com.internship.todo.web;

import com.internship.todo.feature.user.dto.UserPostRequest;
import com.internship.todo.feature.user.service.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

  private final UserServiceImp service;

  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody UserPostRequest createUserDto) {
    service.createUser(createUserDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
