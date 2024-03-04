package com.spring.todoapplication.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
}
