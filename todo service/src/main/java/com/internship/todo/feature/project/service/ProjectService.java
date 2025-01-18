package com.internship.todo.feature.project.service;

import com.internship.todo.feature.project.dto.ProjectPostPutRequest;
import com.internship.todo.feature.project.dto.ProjectView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

  Page<ProjectView> getProjects(Pageable dto , String filterByName);

  ProjectView getProject(Long id);

  ProjectView createProject(ProjectPostPutRequest dto);

  void updateProject(Long id, ProjectPostPutRequest dto);

  void deleteProject(Long id);
}
