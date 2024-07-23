package com.todo.todo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ProjectEntity extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @OneToMany(
      mappedBy = "project",
      orphanRemoval = true,
      cascade = {CascadeType.REMOVE}
  )
  private List<TaskEntity> projects;

  @ManyToOne
  private UserEntity user;
}
