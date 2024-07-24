package com.internship.todo.feature.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskView{

    private Long id;
    private String name;
    private String description;
    private Boolean done;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long projectId;

}
