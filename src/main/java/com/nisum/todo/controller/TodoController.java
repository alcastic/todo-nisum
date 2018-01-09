package com.nisum.todo.controller;

import com.nisum.todo.entity.Todo;
import com.nisum.todo.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    @Autowired
    private ITodoService todoService;

    @PostMapping("todos")
    public ResponseEntity<Void> createTodo(@RequestBody Todo todo) {
        boolean created = todoService.createTodo(todo);
        return created ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("todos")
    public ResponseEntity<List<Todo>> loadAllTodos() {
        return new ResponseEntity<>(todoService.loadAllTodos(), HttpStatus.OK);
    }

    @GetMapping("todos/{id}")
    public ResponseEntity<Todo> loadTodoById(@PathVariable String id) {
        Todo todo = todoService.loadTodoBtId(id);
        return todo != null ? new ResponseEntity<>(todo, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("todos")
    public ResponseEntity<Void> updateTodo(@RequestBody Todo todo) {
        boolean inserted = todoService.updateTodo(todo);
        return inserted ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("todos/{id}")
    public ResponseEntity<Todo> deleteTodoById(@PathVariable String id) {
        Todo todo = todoService.deleteTodoById(id);
        return null != todo ? new ResponseEntity<>(todo, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
