package com.internship.todo.infrastructure.shared.mappers;

import com.internship.todo.feature.task.dto.TaskPostRequest;
import com.internship.todo.feature.task.dto.TaskView;
import com.internship.todo.feature.task.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , implementationName = "TaskMapperImpl")
public interface TaskMapper {

  @Mapping(target = "projectId", source = "entity.project.id")
  @Mapping(target = "createdAt", source = "entity.creationDate")
  @Mapping(target = "updatedAt", source = "entity.updatedDate")
  TaskView toTask(Task entity);

  Task toTaskEntity(TaskPostRequest dto);

}
