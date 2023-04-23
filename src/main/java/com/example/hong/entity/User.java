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
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String password;

    //일반 계정, 구글, 네이버, 카카오
    private String provider;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Board> board = new ArrayList<>();

    @Builder
    public User(Long id, String email,String name,String address ,String password, String provider, Role role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address=address;
        this.password = password;
        this.provider = provider;
        this.role = Role.USER;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public void updatePw(String password) {
        this.password = password;
    }

    public void update(String password, String name) {
        this.password = password;
        this.name = name;
    }

    //Oauth2
    public User updateName(String name) {
        this.name = name;

        return this;
    }

    //Oauth2
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .build();
    }

}
