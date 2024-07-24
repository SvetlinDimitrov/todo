package com.internship.todo.feature.project.service;

import com.internship.todo.feature.project.dto.ProjectPostRequest;
import com.internship.todo.feature.project.dto.ProjectPutRequest;
import com.internship.todo.feature.project.dto.ProjectView;
import org.springframework.data.domain.Page;

public interface ProjectService {

  Page<ProjectView> getProjects(int page, int size);

  ProjectView getProject(Long id);

  void createProject(ProjectPostRequest dto);

  void updateProject(Long id, ProjectPutRequest dto);

  void deleteProject(Long id);
}
