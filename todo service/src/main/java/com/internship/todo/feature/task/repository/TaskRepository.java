package com.internship.todo.feature.task.repository;

import com.internship.todo.feature.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

  Optional<Task> findByIdAndProject_User_Email(Long id, String userEmail);
}
