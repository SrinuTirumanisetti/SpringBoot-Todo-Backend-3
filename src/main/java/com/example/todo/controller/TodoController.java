/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 * 
 */

// Write your code here

package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoJpaService;

@RestController
public class TodoController {

    @Autowired
    private TodoJpaService todoService;

    // API 1: Get all todos
    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    // API 2: Create a new todo
    @PostMapping("/todos")
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    // API 3: Get todo by id
    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable int id) {
        try {
            return todoService.getTodoById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        }
    }

    // API 4: Update todo by id
    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable int id, @RequestBody Map<String, String> updates) {
        try {
            return todoService.updateTodo(id, updates);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        }
    }

    // API 5: Delete todo by id
    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable int id) {
        try {
            todoService.deleteTodo(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        }
    }
}
