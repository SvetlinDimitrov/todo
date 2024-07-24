package com.internship.todo.feature.project.entity;

import com.internship.todo.feature.task.entity.TaskEntity;
import com.internship.todo.feature.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "projects")
public class ProjectEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @OneToMany(
      mappedBy = "project",
      orphanRemoval = true,
      cascade = {CascadeType.REMOVE}
  )
  private List<TaskEntity> tasks;

  @ManyToOne
  private UserEntity user;
}
