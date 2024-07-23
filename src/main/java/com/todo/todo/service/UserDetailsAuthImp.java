package com.todo.todo.service;

import com.todo.todo.model.views.User;
import com.todo.todo.repository.user.UserRepository;
import com.todo.todo.security.UserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsAuthImp implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email)
        .map(this::toUserAuth)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  public String getUsernameFromUserSecurity() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }

  private UserAuth toUserAuth(User user) {
    return new UserAuth(user);
  }
}
