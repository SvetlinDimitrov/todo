package com.internship.todo.feature.project.dto;

import com.internship.todo.feature.task.dto.TaskView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectView {

  private Long id;
  private String name;
  private List<TaskView> tasks;
  private String userEmail;
}
