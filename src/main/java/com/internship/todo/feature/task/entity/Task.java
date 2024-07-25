package com.internship.todo.feature.task.entity;

import com.internship.todo.feature.project.entity.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {

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
  private Project project;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Task task = (Task) o;
    return Objects.equals(id, task.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Task{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", done=" + done +
        ", creationDate=" + creationDate +
        ", updatedDate=" + updatedDate +
        ", project=" + project +
        '}';
  }
}
