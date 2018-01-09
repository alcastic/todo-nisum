package com.nisum.todo.service;

import com.nisum.todo.entity.Todo;

import java.util.List;

public interface ITodoService {

    boolean createTodo(Todo todo);

    List<Todo> loadAllTodos();

    Todo loadTodoBtId(String id);

    boolean updateTodo(Todo todo);

    Todo deleteTodoById(String id);
}
