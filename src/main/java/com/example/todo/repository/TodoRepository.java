package com.example.todo.repository;

import com.example.todo.model.Todo;
import java.util.*;

public interface TodoRepository {
    List<Todo> findAll();
    Todo save(Todo todo);
    Optional<Todo> findById(int id);
    void deleteById(int id);
}
