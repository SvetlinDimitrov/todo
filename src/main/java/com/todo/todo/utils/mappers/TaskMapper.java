package com.todo.todo.utils.mappers;

import com.todo.todo.model.dto.CreateTask;
import com.todo.todo.model.entity.ProjectEntity;
import com.todo.todo.model.entity.TaskEntity;
import com.todo.todo.model.views.Task;

public class TaskMapper {

  public static Task toTask(TaskEntity taskEntity) {
    return new Task(taskEntity.getId(), taskEntity.getName(), taskEntity.getDescription(), taskEntity.getDone() , taskEntity.getProject().getId());
  }

  public static TaskEntity toTaskEntity(Task task , ProjectEntity project) {
    TaskEntity entity = new TaskEntity();

    entity.setId(task.id());
    entity.setName(task.name());
    entity.setDescription(task.description());
    entity.setDone(task.done());
    entity.setProject(project);

    return entity;
  }

  public static Task toTask(CreateTask dto , Long projectId) {
    return new Task(null, dto.name(), dto.description(), false , projectId);
  }
}
