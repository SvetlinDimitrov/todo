package com.internship.todo.feature.task.repository;

import com.internship.todo.feature.task.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task, Long> {

  Page<Task> findAllByProject_IdAndProject_User_Email(Long projectId, String userEmail, Pageable pageable);

  Optional<Task> findByIdAndProject_User_Email(Long id, String userEmail);
}
