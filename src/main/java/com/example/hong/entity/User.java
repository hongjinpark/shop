package com.example.hong.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    private String email;
    private int age;
    private String password;


    @Builder
    public User(String id, String email, int age, String password) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    public void updatePw(String password) {
        this.password = password;
    }

}
