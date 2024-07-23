package com.todo.todo.model.views;

import java.util.List;

public record Project(Long id , String name , List<Task> tasks , String userEmail) {
}
