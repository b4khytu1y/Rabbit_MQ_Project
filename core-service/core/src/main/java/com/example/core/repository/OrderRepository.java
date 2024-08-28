package com.example.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.core.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
