package com.todo.todo.repository.project;

import com.todo.todo.model.views.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProjectRepository {

  void save(Project project);

  void update(Project project);

  void delete(Project project);

  Optional<Project> findByIdAndUserEmail(Long id , String userEmail);

  Page<Project> findAllByUserEmail(Pageable pageable, String userEmail);
}
