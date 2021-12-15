package com.example.atmserver.demo.security.repository;


import com.example.atmserver.demo.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}
