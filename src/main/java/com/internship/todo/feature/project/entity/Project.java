package com.internship.todo.feature.project.entity;

import com.internship.todo.feature.task.entity.Task;
import com.internship.todo.feature.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {

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
  private List<Task> tasks;

  @ManyToOne
  private User user;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Project project = (Project) o;
    return Objects.equals(id, project.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Project{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", tasks=" + tasks +
        ", user=" + user +
        '}';
  }
}
