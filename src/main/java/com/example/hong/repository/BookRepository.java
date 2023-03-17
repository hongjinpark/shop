package com.example.hong.repository;


import com.example.hong.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {


    List<Book> findAll();
}