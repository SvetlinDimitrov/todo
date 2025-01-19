package com.todo.userservice.entity;

import jakarta.persistence.MappedSuperclass;
import java.sql.Timestamp;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@MappedSuperclass
public abstract class AuditableEntity extends BaseEntity {

  private Timestamp createdAt;
  private Timestamp updatedAt;
}