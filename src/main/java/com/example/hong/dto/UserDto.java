package com.example.hong.dto;


import com.example.hong.entity.User;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {



    private String id;
//    @Email(message = "올바른 이메일 주소를 입력해주세요")
    private String email;
    private String name;
    private String address;
    private String password;

    public User toEntity(){
        return User.builder()
                .id(id)
                .email(email)
                .name(name)
                .address(address)
                .password(password)
                .build();
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
