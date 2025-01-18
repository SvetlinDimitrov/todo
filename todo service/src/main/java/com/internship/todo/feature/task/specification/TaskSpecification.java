package com.internship.todo.feature.task.specification;

import com.internship.todo.feature.task.dto.TaskFilter;
import com.internship.todo.feature.task.entity.Task;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {

  public static Specification<Task> filterByCriteria(TaskFilter filterCriteria, String userEmail) {
    return (root, query, criteriaBuilder) -> {
      Predicate predicate = criteriaBuilder.conjunction();

      if (filterCriteria.projectId() != null) {
        predicate = criteriaBuilder.and(
            predicate,
            criteriaBuilder.equal(root.get("project").get("id"), filterCriteria.projectId())
        );
      }

      if (filterCriteria.name() != null && !filterCriteria.name().isBlank()) {
        predicate = criteriaBuilder.and(
            predicate,
            criteriaBuilder.like(root.get("name"), "%" + filterCriteria.name() + "%")
        );
      }

      predicate = criteriaBuilder.and(
          predicate,
          criteriaBuilder.equal(root.get("project").get("user").get("email"), userEmail)
      );

      return predicate;
    };
  }
}