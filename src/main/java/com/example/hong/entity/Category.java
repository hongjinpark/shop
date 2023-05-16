package com.example.hong.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name="category")
public class Category {
    @Id
    @Column(name="category_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column( name="name", nullable=false, length=100 )
    private String name; // 카테고리 이름

    @Column( name="tier", nullable=false, length=100 )
    private String tier; // 카테고리 등급

    @OneToMany(mappedBy="category")
    private List<Item> items = new ArrayList<>();

    // https://kimvampa.tistory.com/197 참조
}