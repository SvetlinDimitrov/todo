package com.todo.userservice.service;

import static com.todo.userservice.exceptions.ExceptionMessages.USER_DETAILS_NOT_FOUND;
import static com.todo.userservice.exceptions.ExceptionMessages.USER_NOT_FOUND;

import com.todo.exceptions.throwable.NotFoundException;
import com.todo.userservice.domain.dto.UserDetailsCreateRequest;
import com.todo.userservice.domain.dto.UserDetailsUpdateRequest;
import com.todo.userservice.domain.dto.UserDetailsView;
import com.todo.userservice.domain.entity.UserDetails;
import com.todo.userservice.mappers.UserDetailsMapper;
import com.todo.userservice.repository.UserDetailsRepository;
import com.todo.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserDetailsRepository repository;
  private final UserRepository userRepository;
  private final UserDetailsMapper mapper;

  public UserDetailsView create(UserDetailsCreateRequest request, Long userId) {
    var entityToSave = mapper.toEntity(request);

    userRepository.findById(userId)
        .ifPresentOrElse(entityToSave::setUser, () -> {
          throw new NotFoundException(USER_NOT_FOUND, userId);
        });

    var savedEntity = repository.save(entityToSave);
    return mapper.toView(savedEntity);
  }

  public Page<UserDetailsView> getAll(Pageable pageable) {
    return repository.findAll(pageable)
        .map(mapper::toView);
  }

  public UserDetailsView getById(Long id) {
    return mapper.toView(findById(id));
  }

  public UserDetailsView update(Long id, UserDetailsUpdateRequest dto) {
    var entityToUpdate = findById(id);
    mapper.updateEntity(entityToUpdate, dto);
    return mapper.toView(repository.save(entityToUpdate));
  }

  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException(USER_DETAILS_NOT_FOUND, id);
    }
    repository.deleteById(id);
  }

  private UserDetails findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new NotFoundException(USER_DETAILS_NOT_FOUND, id));
  }
}