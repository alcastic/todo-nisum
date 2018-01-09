package com.nisum.todo.service;

import com.nisum.todo.entity.Todo;
import com.nisum.todo.repository.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {
    @Mock
    TodoRepository todoRepository;

    @InjectMocks
    TodoService todoService;

    @Before
    public void setUp() {
        when(todoRepository.exists("id_non_exist")).thenReturn(false);
        when(todoRepository.exists("id_that_exist")).thenReturn(true);
    }

    @Test
    public void shouldReturnTrue_whenCreatingATodoWithIdNonExists() {
        // arrange
        Todo todo = new Todo();
        todo.setId("id_non_exist");
        todo.setTitle("title");
        todo.setDone(false);
        // act
        boolean result = todoService.createTodo(todo);
        // assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalse_whenCreatingATodoWithIdNonExists() {
        // arrange
        Todo todo = new Todo();
        todo.setId("id_that_exist");
        todo.setTitle("title");
        todo.setDone(false);
        // act
        boolean result = todoService.createTodo(todo);
        // assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrue_whenCreatingATodoWithNullId() {
        // arrange
        Todo todo = new Todo();
        todo.setId(null);
        todo.setTitle("title");
        todo.setDone(false);
        // act
        boolean result = todoService.createTodo(todo);
        //assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnAEmptyTodoList_whenNoTodo() {
        // arrange
        int expected = 0;
        when(todoRepository.findAll()).thenReturn(Collections.emptyList());
        // act
        int result = todoService.loadAllTodos().size();
        // assert
        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnANonEmptyTodoList_whenTodos() {
        // arrange
        int expected = 1;
        when(todoRepository.findAll()).thenReturn(Arrays.asList(mock(Todo.class)));
        // act
        int result = todoService.loadAllTodos().size();
        // assert
        assertEquals(expected, result);
    }

    @Test
    public void loadTodoById_shouldReturnNull_whenTodoNonExists() {
        when(todoRepository.findOne(anyString())).thenReturn(null);
        Todo todo = todoService.loadTodoBtId("unknownid");
        assertNull(todo);
    }

    @Test
    public void loadTodoById_shouldNotReturnNull_whenTodoNonExists() {
        when(todoRepository.findOne(anyString())).thenReturn(mock(Todo.class));
        Todo todo = todoService.loadTodoBtId("unknownid");
        assertNotNull(todo);
    }

    //--
    @Test
    public void updateTodo_shouldReturnFalse_whenTodoHasNotId() {
        // arrange
        Todo todo = new Todo();
        todo.setId(null);
        todo.setTitle("title");
        todo.setDone(false);
        // act
        boolean result = todoService.updateTodo(todo);
        // assert
        assertFalse(result);
    }

    @Test
    public void updateTodo_shouldReturnFalse_whenNonExistsTodoWithProvidedId() {
        // arrange
        Todo todo = new Todo();
        todo.setId("id_non_exist");
        todo.setTitle("title");
        todo.setDone(false);
        // act
        boolean result = todoService.updateTodo(todo);
        // assert
        assertFalse(result);
    }

    @Test
    public void updateTodo_shouldReturnTrue_whenNonExistsTodoWithProvidedId() {
        // arrange
        Todo todo = new Todo();
        todo.setId("id_that_exist");
        todo.setTitle("title");
        todo.setDone(false);
        // act
        boolean result = todoService.updateTodo(todo);
        // assert
        assertTrue(result);
    }


    @Test
    public void deleteTodoById_shouldReturnNull_whenNonExistsTodoWithProvidedId() {
        // act
        Todo result = todoService.deleteTodoById("id_non_exist");
        // assert
        assertNull(result);
    }

    @Test
    public void deleteTodoById_shouldReturnNotNull_whenExistsTodoWithProvidedId() {
        // arrange
        when(todoRepository.findOne(anyString())).thenReturn(mock(Todo.class));
        // act
        Todo result = todoService.deleteTodoById("id_non_exist");
        // assert
        assertNotNull(result);
    }
}
