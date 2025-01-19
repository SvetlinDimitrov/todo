package com.todo.userservice.web;

import com.todo.userservice.domain.dto.UserDetailsCreateRequest;
import com.todo.userservice.domain.dto.UserDetailsUpdateRequest;
import com.todo.userservice.domain.dto.UserDetailsView;
import com.todo.userservice.service.UserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-details")
@RequiredArgsConstructor
public class UserDetailsController {

  private final UserDetailsService userDetailsService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDetailsView create(
      @RequestParam Long userId,
      @Valid @RequestBody UserDetailsCreateRequest request) {
    return userDetailsService.create(request, userId);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<UserDetailsView> getAll(Pageable pageable) {
    return userDetailsService.getAll(pageable);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public UserDetailsView getById(
      @PathVariable Long id) {
    return userDetailsService.getById(id);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public UserDetailsView update(
      @PathVariable Long id,
      @Valid @RequestBody UserDetailsUpdateRequest request) {
    return userDetailsService.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(
      @PathVariable Long id
  ) {
    userDetailsService.delete(id);
  }
}