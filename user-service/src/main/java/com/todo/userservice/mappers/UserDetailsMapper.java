package com.todo.userservice.mappers;

import com.todo.userservice.domain.dto.UserDetailsCreateRequest;
import com.todo.userservice.domain.dto.UserDetailsUpdateRequest;
import com.todo.userservice.domain.dto.UserDetailsView;
import com.todo.userservice.domain.entity.UserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserDetailsMapper {

  UserDetailsView toView(UserDetails entity);

  UserDetails toEntity(UserDetailsCreateRequest dto);

  void updateEntity(@MappingTarget UserDetails entity, UserDetailsUpdateRequest dto);
}
