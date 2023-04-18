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
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String name;
    private String address;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Board> board=new ArrayList<>();

    @Builder
    public User(Long id, String email,String name,String address ,String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address=address;
        this.password = password;
        this.role=Role.USER;
    }

    public void updatePw(String password) {
        this.password = password;
    }

}
