package com.internship.todo.feature.task.entity;

import com.internship.todo.feature.project.entity.ProjectEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tasks")
public class TaskEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column
  private String description;

  @Column
  private Boolean done = false;

  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  @Column(name = "updated_date")
  private LocalDateTime updatedDate;

  @ManyToOne
  private ProjectEntity project;
}
