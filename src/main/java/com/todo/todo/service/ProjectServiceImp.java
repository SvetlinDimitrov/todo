package com.todo.todo.service;

import com.todo.todo.exceptions.BadResponseException;
import com.todo.todo.model.dto.CreateProject;
import com.todo.todo.model.dto.UpdateProject;
import com.todo.todo.model.views.Project;
import com.todo.todo.repository.project.ProjectRepository;
import com.todo.todo.utils.mappers.ProjectMapper;
import com.todo.todo.utils.validator.CreateProjectValidator;
import com.todo.todo.utils.validator.PageableValidator;
import com.todo.todo.utils.validator.UpdateProjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp {

  private final ProjectRepository projectRepository;
  private final UserDetailsAuthImp userDetailsAuthImp;

  public Page<Project> getProjects(int page, int size) {

    PageableValidator.validate(page, size);

    Pageable pageable = PageRequest.of(page, size);

    return projectRepository.findAllByUserEmail(pageable, userDetailsAuthImp.getUsernameFromUserSecurity());
  }

  public Project getProject(Long id) {
    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    return projectRepository.findByIdAndUserEmail(id, userEmail)
        .orElseThrow(() -> new BadResponseException("Task not found"));
  }

  public void createProject(CreateProject dto) {

    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    CreateProjectValidator.validate(dto);

    Project project = ProjectMapper.toProject(dto, userEmail);

    projectRepository.save(project);
  }

  public void updateProject(Long id, UpdateProject dto) {
    UpdateProjectValidator.validate(dto);

    Project project = getProject(id);
    Project projectToUpdate = new Project(project.id(), dto.name(), project.tasks(), project.userEmail());

    projectRepository.update(projectToUpdate);
  }

  public void deleteProject(Long id) {
    Project project = getProject(id);

    projectRepository.delete(project);
  }

}
