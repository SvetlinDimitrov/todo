package com.todo.todo.repository.project;

import com.todo.todo.model.entity.ProjectEntity;
import com.todo.todo.model.entity.UserEntity;
import com.todo.todo.model.views.Project;
import com.todo.todo.repository.user.UserRepositoryJpa;
import com.todo.todo.utils.mappers.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProjectRepositoryImp implements ProjectRepository {

  private final ProjectRepositoryJpa repositoryJpa;
  private final UserRepositoryJpa userRepositoryJpa;

  @Override
  public void save(Project project) {
    ProjectEntity projectEntity = getProjectEntity(project);

    repositoryJpa.save(projectEntity);
  }

  @Override
  public void update(Project project) {
    save(project);
  }

  @Override
  public void delete(Project project) {
    ProjectEntity projectEntity = getProjectEntity(project);

    repositoryJpa.delete(projectEntity);
  }

  @Override
  public Optional<Project> findByIdAndUserEmail(Long id, String userEmail) {
    return repositoryJpa.findByIdAndUser_Email(id, userEmail)
        .map(ProjectMapper::toProject);
  }

  @Override
  public Page<Project> findAllByUserEmail(Pageable pageable, String userEmail) {
    return repositoryJpa.findAllByUser_Email(userEmail, pageable)
        .map(ProjectMapper::toProject);
  }

  private ProjectEntity getProjectEntity(Project project) {
    UserEntity user = userRepositoryJpa.findByEmail(project.userEmail())
        .orElseThrow(() -> new RuntimeException("User not found"));

    return ProjectMapper.toProjectEntity(project, user);
  }
}
