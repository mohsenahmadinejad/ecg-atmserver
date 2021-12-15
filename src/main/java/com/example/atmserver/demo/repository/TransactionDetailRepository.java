package com.example.atmserver.demo.repository;

import com.example.atmserver.demo.model.Card;
import com.example.atmserver.demo.model.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {
}
