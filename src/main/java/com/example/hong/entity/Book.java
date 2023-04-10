package com.example.hong.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    private String name;

    private String title;
    private int price;
    private String publisher;



    @Builder
    public Book(String name, String title, int price, String publisher) {
        this.name = name;
        this.title = title;
        this.price = price;
        this.publisher = publisher;
    }

    public void update(String name, String title, int price, String publisher) {
        this.name = name;
        this.title = title;
        this.price = price;
        this.publisher = publisher;
    }
}