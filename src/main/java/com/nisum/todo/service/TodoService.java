package com.nisum.todo.service;

import com.nisum.todo.entity.Todo;
import com.nisum.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TodoService implements ITodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public boolean createTodo(Todo todo) {
        if (null == todo.getId() || !todoRepository.exists(todo.getId())) {
            todoRepository.save(todo);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Todo> loadAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo loadTodoBtId(String id) {
        return todoRepository.findOne(id);
    }

    @Override
    public boolean updateTodo(Todo todo) {
        if (null != todo.getId() && todoRepository.exists(todo.getId())) {
            todoRepository.save(todo);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Todo deleteTodoById(String id) {
        Todo todo = todoRepository.findOne(id);
        if (null != todo) {
            todoRepository.delete(todo);
            return todo;
        } else {
            return null;
        }
    }
}
