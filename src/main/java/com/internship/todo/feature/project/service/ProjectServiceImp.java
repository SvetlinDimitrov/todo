package com.internship.todo.feature.project.service;

import com.internship.todo.feature.project.dto.ProjectPostRequest;
import com.internship.todo.feature.project.dto.ProjectPutRequest;
import com.internship.todo.feature.project.dto.ProjectView;
import com.internship.todo.feature.project.entity.ProjectEntity;
import com.internship.todo.feature.project.repository.ProjectRepository;
import com.internship.todo.feature.project.utils.mappers.ProjectMapper;
import com.internship.todo.feature.project.utils.validators.CreateProjectValidator;
import com.internship.todo.feature.project.utils.validators.UpdateProjectValidator;
import com.internship.todo.feature.user.entity.UserEntity;
import com.internship.todo.feature.user.repository.UserRepository;
import com.internship.todo.infrastructure.security.service.UserDetailsAuthImp;
import com.internship.todo.infrastructure.shared.exceptions.BadResponseException;
import com.internship.todo.infrastructure.shared.validatiors.PageableValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService {

  private final ProjectRepository projectRepository;
  private final UserRepository userRepository;
  private final UserDetailsAuthImp userDetailsAuthImp;

  public Page<ProjectView> getProjects(int page, int size) {

    PageableValidator.validate(page, size);

    Pageable pageable = PageRequest.of(page, size);

    return projectRepository
        .findAllByUser_Email(userDetailsAuthImp.getUsernameFromUserSecurity(), pageable)
        .map(ProjectMapper::toProject);
  }

  public ProjectView getProject(Long id) {

    return ProjectMapper.toProject(getEntityByIdAndUserEmail(id));
  }

  public void createProject(ProjectPostRequest dto) {

    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    UserEntity user = userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new BadResponseException("User not found"));

    CreateProjectValidator.validate(dto);

    ProjectEntity projectToUpdateAndSave = ProjectMapper.toProjectEntity(dto);
    projectToUpdateAndSave.setProjects(new ArrayList<>());
    projectToUpdateAndSave.setUser(user);

    projectRepository.save(projectToUpdateAndSave);
  }

  public void updateProject(Long id, ProjectPutRequest dto) {

    UpdateProjectValidator.validate(dto);

    ProjectEntity projectToSave = getEntityByIdAndUserEmail(id);
    projectToSave.setName(dto.name());

    projectRepository.save(projectToSave);
  }

  public void deleteProject(Long id) {

    projectRepository.delete(getEntityByIdAndUserEmail(id));
  }

  private ProjectEntity getEntityByIdAndUserEmail(Long id) {

    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    return projectRepository.findByIdAndUser_Email(id, userEmail)
        .orElseThrow(() -> new BadResponseException("Project not found"));
  }
}
