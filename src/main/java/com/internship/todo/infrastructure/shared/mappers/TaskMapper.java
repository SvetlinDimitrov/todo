package com.internship.todo.infrastructure.shared.mappers;

import com.internship.todo.feature.task.dto.TaskPostRequest;
import com.internship.todo.feature.task.dto.TaskView;
import com.internship.todo.feature.task.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , implementationName = "TaskMapperImpl")
public interface TaskMapper {

  @Mapping(target = "projectId", source = "entity.project.id")
  TaskView toTask(TaskEntity entity);

  TaskEntity toTaskEntity(TaskPostRequest dto);

}
