package com.example.atmserver.demo.repository;

import com.example.atmserver.demo.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardNumber(String id);
}
