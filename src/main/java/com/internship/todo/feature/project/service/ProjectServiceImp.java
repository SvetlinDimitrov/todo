package com.internship.todo.feature.project.service;

import com.internship.todo.feature.project.dto.ProjectPostPutRequest;
import com.internship.todo.feature.project.dto.ProjectView;
import com.internship.todo.feature.project.entity.Project;
import com.internship.todo.feature.project.repository.ProjectRepository;
import com.internship.todo.feature.user.entity.User;
import com.internship.todo.feature.user.repository.UserRepository;
import com.internship.todo.infrastructure.security.service.UserDetailsAuthImp;
import com.internship.todo.infrastructure.shared.enums.ExceptionMessages;
import com.internship.todo.infrastructure.shared.exceptions.ProjectNotFoundException;
import com.internship.todo.infrastructure.shared.exceptions.UserNotFoundException;
import com.internship.todo.infrastructure.shared.mappers.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService {

  private final ProjectRepository projectRepository;
  private final UserRepository userRepository;
  private final UserDetailsAuthImp userDetailsAuthImp;
  private final ProjectMapper projectMapper;

  public Page<ProjectView> getProjects(Pageable pageable, String filterByName) {
    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    return Optional.ofNullable(filterByName)
        .map(name -> projectRepository
            .findAllByUser_EmailAndNameContaining(userEmail, name, pageable)
            .map(projectMapper::toProjectView)
        )
        .orElse(projectRepository
            .findAllByUser_Email(userEmail, pageable)
            .map(projectMapper::toProjectView));
  }

  public ProjectView getProject(Long id) {

    return projectMapper.toProjectView(getEntityByIdAndUserEmail(id));
  }

  public ProjectView createProject(ProjectPostPutRequest dto) {

    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    User user = userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new UserNotFoundException(
            String.format(ExceptionMessages.USER_NOT_FOUND.getMessage(), userEmail)
        ));

    Project projectToCreate = projectMapper.toProjectEntity(dto);
    projectToCreate.setTasks(new ArrayList<>());
    projectToCreate.setUser(user);

    return projectMapper.toProjectView(projectRepository.save(projectToCreate));
  }

  public void updateProject(Long id, ProjectPostPutRequest dto) {

    Project projectToUpdateAndSave = getEntityByIdAndUserEmail(id);
    projectToUpdateAndSave.setName(dto.name());

    projectRepository.save(projectToUpdateAndSave);
  }

  public void deleteProject(Long id) {

    projectRepository.delete(getEntityByIdAndUserEmail(id));
  }

  private Project getEntityByIdAndUserEmail(Long id) {

    String userEmail = userDetailsAuthImp.getUsernameFromUserSecurity();

    return projectRepository.findByIdAndUser_Email(id, userEmail)
        .orElseThrow(() -> new ProjectNotFoundException(
            String.format(ExceptionMessages.PROJECT_NOT_FOUND.getMessage(), id, userEmail)
        ));
  }
}
