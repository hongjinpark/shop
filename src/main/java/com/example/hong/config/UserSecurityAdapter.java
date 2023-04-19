package com.example.hong.config;

import com.example.hong.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class UserSecurityAdapter extends org.springframework.security.core.userdetails.User {

    private User user;

    public UserSecurityAdapter(User user) {
        super(user.getName(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.user = user;
    }
}