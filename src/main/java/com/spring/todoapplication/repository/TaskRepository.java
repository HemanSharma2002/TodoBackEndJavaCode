package com.spring.todoapplication.repository;


import com.spring.todoapplication.repository.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Todo,Long> {
}
