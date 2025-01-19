package com.todo.userservice.domain.dto;

import com.todo.dto.user.UserView;
import java.sql.Timestamp;

public record UserDetailsView(
    Long id,
    String username,
    Timestamp createdAt,
    Timestamp updatedAt,
    UserView user
) {

}
