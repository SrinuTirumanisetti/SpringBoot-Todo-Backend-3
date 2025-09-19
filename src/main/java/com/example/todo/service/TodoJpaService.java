/*
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.TodoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TodoJpaService implements TodoRepository {

    @Autowired
    private TodoJpaRepository todoJpaRepository;

    @Override
    public List<Todo> findAll() {
        return todoJpaRepository.findAll();
    }

    @Override
    public Todo save(Todo todo) {
        return todoJpaRepository.save(todo);
    }

    @Override
    public Optional<Todo> findById(int id) {
        return todoJpaRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        todoJpaRepository.deleteById(id);
    }

    // Custom Methods for Controller
    public List<Todo> getAllTodos() {
        return findAll();
    }

    public Todo createTodo(Todo todo) {
        return save(todo);
    }

    public Todo getTodoById(int id) {
        return findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Todo updateTodo(int id, Map<String, String> updates) {
        Todo existingTodo = findById(id).orElseThrow(NoSuchElementException::new);

        if (updates.containsKey("todo")) {
            existingTodo.setTodo(updates.get("todo"));
        }
        if (updates.containsKey("status")) {
            existingTodo.setStatus(updates.get("status"));
        }
        if (updates.containsKey("priority")) {
            existingTodo.setPriority(updates.get("priority"));
        }

        return save(existingTodo);
    }

    public void deleteTodo(int id) {
        Todo existingTodo = findById(id).orElseThrow(NoSuchElementException::new);
        deleteById(existingTodo.getId());
    }
}
