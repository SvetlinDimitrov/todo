package com.todo.userservice.service;

import com.todo.userservice.domain.dto.UserDetailsCreateRequest;
import com.todo.userservice.domain.dto.UserDetailsUpdateRequest;
import com.todo.userservice.domain.dto.UserDetailsView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDetailsService {

  UserDetailsView create(UserDetailsCreateRequest request , Long userId);

  Page<UserDetailsView> getAll(Pageable pageable);

  UserDetailsView getById(Long id);

  UserDetailsView update(Long id, UserDetailsUpdateRequest dto);

  void delete(Long id);
}