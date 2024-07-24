package com.internship.todo.infrastructure.shared.mappers;

import com.internship.todo.feature.user.dto.UserPostRequest;
import com.internship.todo.feature.user.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , implementationName = "UserMapperImpl")
public interface UserMapper {

  UserEntity toUserEntity(UserPostRequest userPostRequest);

}
