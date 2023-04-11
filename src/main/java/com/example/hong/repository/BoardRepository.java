package com.example.hong.repository;

import com.example.hong.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findAllByNameOrderByIdDesc(String name);
    List<Board> findAllByOrderByIdDesc();
}