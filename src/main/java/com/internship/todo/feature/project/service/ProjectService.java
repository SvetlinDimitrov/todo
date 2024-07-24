package com.internship.todo.feature.project.service;

import com.internship.todo.feature.project.dto.ProjectPostPutRequest;
import com.internship.todo.feature.project.dto.ProjectView;
import org.springframework.data.domain.Page;

public interface ProjectService {

  Page<ProjectView> getProjects(int page, int size);

  ProjectView getProject(Long id);

  void createProject(ProjectPostPutRequest dto);

  void updateProject(Long id, ProjectPostPutRequest dto);

  void deleteProject(Long id);
}
