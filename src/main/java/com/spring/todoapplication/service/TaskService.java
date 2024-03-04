package com.spring.todoapplication.service;

import com.spring.todoapplication.repository.TaskRepository;
import com.spring.todoapplication.repository.UserRepository;
import com.spring.todoapplication.repository.entity.Todo;
import com.spring.todoapplication.repository.entity.User;
import com.spring.todoapplication.repository.entity.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private TaskRepository taskRepository;

    public TaskService(PasswordEncoder passwordEncoder, UserRepository userRepository, TaskRepository taskRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public boolean signUp(UserModel userModel) {
        User user=new User(userModel.getUsername(),passwordEncoder.encode(userModel.getPassword()), userModel.getFirstName(), userModel.getLastName());
        user.setRole("USER");
        if(userRepository.save(user)==null){
            return false;
        }
        return true;
    }

    public boolean addTodo(Todo todo, String username) {
        System.out.println("Up");
        User user=userRepository.findByUsername(username);
        System.out.println(user.toString());
        if(user==null)
            return false;
        todo.setUser(user);
        todo.setDone(false);
        System.out.println(todo.toString());
        Todo todo1=taskRepository.save(todo);
        if(todo1==null)
            return  false;
        return true;
    }

    public List<Todo> getAllTodos(String username) {
        return userRepository.findByUsername(username).getTodos();
    }

    public Todo getTodo(String username, Long id) {
        return taskRepository.findById(id).get();
    }

    public boolean deleteTodo(String username, Long id) {
        taskRepository.deleteById(id);
        return true;
    }

    public Boolean updateTodo(Todo todo, String username, Long id) {
        User user=userRepository.findByUsername(username);
        Todo todos=taskRepository.findById(id).get();
        if(todos.getUser()==user){
            todo.setUser(user);
            taskRepository.save(todo);
            return true;
        }

        return false;
    }
}
