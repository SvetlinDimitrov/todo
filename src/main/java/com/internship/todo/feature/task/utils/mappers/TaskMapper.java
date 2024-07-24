package com.internship.todo.feature.task.utils.mappers;

import com.internship.todo.feature.task.dto.TaskPostRequest;
import com.internship.todo.feature.task.dto.TaskView;
import com.internship.todo.feature.task.entity.TaskEntity;

public class TaskMapper {

  public static TaskView toTask(TaskEntity taskEntity) {
    return new TaskView(taskEntity.getId(), taskEntity.getName(), taskEntity.getDescription(), taskEntity.getDone(), taskEntity.getProject().getId());
  }

  public static TaskEntity toTaskEntity(TaskPostRequest task) {
    TaskEntity entity = new TaskEntity();

    entity.setId(null);
    entity.setName(task.name());
    entity.setDescription(task.description());
    entity.setDone(task.done());
    entity.setProject(null);

    return entity;
  }
}
