package com.todo.todo.utils.mappers;

import com.todo.todo.model.dto.CreateProject;
import com.todo.todo.model.entity.ProjectEntity;
import com.todo.todo.model.entity.UserEntity;
import com.todo.todo.model.views.Project;

import java.util.ArrayList;

public class ProjectMapper {

  public static Project toProject(ProjectEntity entity) {
    return new Project(
        entity.getId(),
        entity.getName(),
        entity.getProjects()
            .stream()
            .map(TaskMapper::toTask)
            .toList(),
        entity.getUser().getEmail()
    );
  }

  public static ProjectEntity toProjectEntity(Project project, UserEntity user) {
    ProjectEntity entity = new ProjectEntity();

    entity.setId(project.id());
    entity.setName(project.name());
    entity.setProjects(
        project.tasks()
            .stream()
            .map(TaskMapper::toTaskEntity)
            .toList()
    );
    entity.setUser(user);

    return entity;
  }

  public static Project toProject(CreateProject dto , String userEmail) {
    return new Project(null, dto.name(), new ArrayList<>(), userEmail);
  }
}
