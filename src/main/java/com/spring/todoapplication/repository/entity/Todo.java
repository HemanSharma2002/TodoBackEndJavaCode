package com.spring.todoapplication.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    @Id
    @SequenceGenerator(name = "todo_sequence",sequenceName ="todo_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "todo_sequence")
    private Long id;
    private String description;
    private boolean isDone;
    private LocalDate dueDate;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user",nullable = false)
    @ToString.Exclude
    private User user;

    public Todo(String description) {
        this.description = description;
    }
}
