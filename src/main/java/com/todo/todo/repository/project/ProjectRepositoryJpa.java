package com.todo.todo.repository.project;

import com.todo.todo.model.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepositoryJpa extends JpaRepository<ProjectEntity, Long> {
  Optional<ProjectEntity> findByIdAndUser_Email(Long id, String userEmail);

  Page<ProjectEntity> findAllByUser_Email(String userEmail, Pageable pageable);
}
