package com.internship.todo.infrastructure.shared.mappers;

import com.internship.todo.feature.user.dto.UserPostRequest;
import com.internship.todo.feature.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , implementationName = "UserMapperImpl")
public interface UserMapper {

  User toUserEntity(UserPostRequest userPostRequest);

}
