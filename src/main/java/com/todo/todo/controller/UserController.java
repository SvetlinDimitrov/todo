package com.todo.todo.controller;

import com.todo.todo.model.dto.CreateUser;
import com.todo.todo.service.UserServiceImp;
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
  public ResponseEntity<?> createUser(@RequestBody CreateUser createUserDto) {
    service.createUser(createUserDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
