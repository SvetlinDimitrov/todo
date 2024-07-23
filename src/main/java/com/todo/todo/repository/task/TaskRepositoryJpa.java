package com.todo.todo.repository.task;

import com.todo.todo.model.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TaskRepositoryJpa extends JpaRepository<TaskEntity, Long> {

  Page<TaskEntity> findAllByProject_IdAndProject_User_Email(Long projectId, String userEmail ,Pageable pageable);

  Optional<TaskEntity> findByIdAndProject_User_Email(Long id , String userEmail);
}
