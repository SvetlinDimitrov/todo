package com.internship.todo.infrastructure.shared.mappers;

import com.internship.todo.feature.project.dto.ProjectPostPutRequest;
import com.internship.todo.feature.project.dto.ProjectView;
import com.internship.todo.feature.project.entity.ProjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring" , uses = TaskMapper.class , implementationName = "ProjectMapperImpl")
public interface ProjectMapper {

  @Mapping(target = "userEmail", source = "entity.user.email")
  ProjectView toProjectView(ProjectEntity entity);

  ProjectEntity toProjectEntity(ProjectPostPutRequest dto);
}
