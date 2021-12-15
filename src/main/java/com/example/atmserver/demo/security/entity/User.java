package com.example.atmserver.demo.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_TBL")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String userName;
    private String password;
    private String email;
    private int failedAttempt;

    public User(String userName, String password, String email, int failedAttempt) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.failedAttempt = failedAttempt;
    }
}
