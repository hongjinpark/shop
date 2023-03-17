package com.example.hong.dto;


import com.example.hong.entity.User;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {



    private String id;

    private String email;
    private int age;
    private String password;

    public User toEntity(){

        return User.builder()
                .id(id)
                .email(email)
                .age(age)
                .password(password)
                .build();
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
