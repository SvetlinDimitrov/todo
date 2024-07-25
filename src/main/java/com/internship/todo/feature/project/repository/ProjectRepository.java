package com.internship.todo.feature.project.repository;

import com.internship.todo.feature.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
  Optional<Project> findByIdAndUser_Email(Long id, String userEmail);

  Page<Project> findAllByUser_Email(String userEmail, Pageable pageable);
}
