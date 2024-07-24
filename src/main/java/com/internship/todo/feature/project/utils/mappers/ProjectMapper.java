package com.internship.todo.feature.project.utils.mappers;

import com.internship.todo.feature.project.dto.ProjectPostPutRequest;
import com.internship.todo.feature.project.dto.ProjectView;
import com.internship.todo.feature.project.entity.ProjectEntity;
import com.internship.todo.feature.task.utils.mappers.TaskMapper;

public class ProjectMapper {

  public static ProjectView toProject(ProjectEntity entity) {
    return new ProjectView(
        entity.getId(),
        entity.getName(),
        entity.getProjects()
            .stream()
            .map(TaskMapper::toTask)
            .toList(),
        entity.getUser().getEmail()
    );
  }

  public static ProjectEntity toProjectEntity(ProjectPostPutRequest project) {
    ProjectEntity entity = new ProjectEntity();

    entity.setId(null);
    entity.setName(project.name());
    entity.setProjects(null);

    return entity;
  }
}
