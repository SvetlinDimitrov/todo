package com.internship.todo.infrastructure.security.service;

import com.internship.todo.feature.user.dto.UserView;
import com.internship.todo.feature.user.repository.UserRepository;
import com.internship.todo.feature.user.utils.mappers.UserMapper;
import com.internship.todo.infrastructure.security.dto.UserDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsAuthImp implements UserDetailsService , UserAuthService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email)
        .map(UserMapper::toUser)
        .map(this::toUserAuth)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  public String getUsernameFromUserSecurity() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }

  private UserDetailsDto toUserAuth(UserView user) {
    return new UserDetailsDto(user);
  }
}
