package com.internship.todo.feature.project.repository;

import com.internship.todo.feature.project.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
  Optional<ProjectEntity> findByIdAndUser_Email(Long id, String userEmail);

  Page<ProjectEntity> findAllByUser_Email(String userEmail, Pageable pageable);
}
