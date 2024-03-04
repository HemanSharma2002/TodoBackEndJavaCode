package com.spring.todoapplication.controler;

import com.spring.todoapplication.repository.entity.Todo;
import com.spring.todoapplication.repository.entity.UserModel;
import com.spring.todoapplication.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TodoControler {
    @Autowired
    private TaskService taskService;

//    @PostMapping("/authenticated")
//    public String authenticated(){
//        return "sucess";
//    }
    @PostMapping("sign-up")
    public boolean signUp(@RequestBody UserModel userModel){
        return taskService.signUp(userModel);
    }
    @GetMapping(path = "/")
    public String retuirnSomthinsg(){
        return "Congo";
    }
    @PostMapping("/users/{username}/todos")
    public boolean addTodo(@RequestBody Todo todo, @PathVariable ("username") String username){
        System.out.println("conytoler");
        return taskService.addTodo(todo,username);
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable ("username") String username){
        return taskService.getAllTodos(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable ("username") String username,@PathVariable ("id") Long id){
        return taskService.getTodo(username,id);

    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public boolean deleteTodo(@PathVariable ("username") String username,@PathVariable ("id") Long id){
        return taskService.deleteTodo(username,id);

    }

    @PutMapping("/users/{username}/todos/{id}")
    public Boolean updateTodo(@RequestBody Todo todo,@PathVariable ("username") String username,@PathVariable ("id") Long id){
        System.out.println("cont");
        return taskService.updateTodo(todo,username,id);

    }
}
