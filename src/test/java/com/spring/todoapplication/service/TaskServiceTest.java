package com.spring.todoapplication.service;

import com.spring.todoapplication.repository.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TaskServiceTest {
    @Autowired
    private TaskService taskService;
    @Test
    void addTodo() {
        Todo todo=new Todo("Hello ");
        taskService.addTodo(todo,"HemanSharma");
    }

    @Test
    void getAllTodos() {
        taskService.getAllTodos("Heman Sharma");
    }
}