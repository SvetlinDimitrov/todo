package com.todo.todo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class UserEntity extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String email;
  @Column(nullable = false)
  private String password;
  @OneToMany(mappedBy = "user")
  private List<ProjectEntity> projects;
}
