package com.example.hong.repository;

import com.example.hong.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long>,CartCustomRepository {
    CartItem findByCart_id(Long id);
}
