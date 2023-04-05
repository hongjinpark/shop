package com.example.hong.entity;


import com.example.hong.constant.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Board> board=new ArrayList<>();

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
