package com.internship.todo.infrastructure.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserAuthService {

  UserDetails loadUserByUsername(String email);

  String getUsernameFromUserSecurity();
}
