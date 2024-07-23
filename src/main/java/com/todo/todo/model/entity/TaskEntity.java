package com.todo.todo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TaskEntity extends BaseEntity {

  @Column(nullable = false)
  private String name;
  @Column
  private String description;
  @Column
  private Boolean done = false;
  @ManyToOne
  private ProjectEntity project;
}
