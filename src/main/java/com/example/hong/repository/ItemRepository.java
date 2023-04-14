package com.example.hong.repository;

import com.example.hong.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Object> findById(Item itemId);
}
