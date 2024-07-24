package com.internship.todo.feature.project.service;

import com.internship.todo.feature.project.dto.ProjectPageableRequest;
import com.internship.todo.feature.project.dto.ProjectPostPutRequest;
import com.internship.todo.feature.project.dto.ProjectView;
import com.internship.todo.feature.project.entity.ProjectEntity;
import com.internship.todo.feature.project.repository.ProjectRepository;
import com.internship.todo.feature.user.entity.UserEntity;
import com.internship.todo.feature.user.repository.UserRepository;
import com.internship.todo.infrastructure.security.service.UserDetailsAuthImp;
import com.internship.todo.infrastructure.shared.exceptions.BadResponseException;
import com.internship.todo.infrastructure.shared.mappers.ProjectMapper;
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
  private final ProjectMapper projectMapper;

  public Page<ProjectView> getProjects(ProjectPageableRequest dto) {

    Pageable pageable = PageRequest.of(
        dto.page() == null ? 0 : dto.page(),
        dto.size() == null ? 10 : dto.size()
    );

    return projectRepository
        .findAllByUser_Email(userDetailsAuthImp.getUsernameFromUserSecurity(), pageable)
        .map(projectMapper::toProjectView);
  }

  public ProjectView getProject(Long id) {

    return projectMapper.toProjectView(getEntityByIdAndUserEmail(id));
  }

  public void createProject(ProjectPostPutRequest dto) {

    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    UserEntity user = userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new BadResponseException("User not found"));

    ProjectEntity projectToCreate = projectMapper.toProjectEntity(dto);
    projectToCreate.setTasks(new ArrayList<>());
    projectToCreate.setUser(user);

    projectRepository.save(projectToCreate);
  }

  public void updateProject(Long id, ProjectPostPutRequest dto) {

    ProjectEntity projectToUpdateAndSave = getEntityByIdAndUserEmail(id);
    projectToUpdateAndSave.setName(dto.name());

    projectRepository.save(projectToUpdateAndSave);
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
