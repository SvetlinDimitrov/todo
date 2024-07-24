package com.internship.todo.feature.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskView{

    private Long id;
    private String name;
    private String description;
    private Boolean done;
    private Long projectId;
}
