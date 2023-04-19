package com.example.hong.controller;


import com.example.hong.config.auth.PrincipalDetail;
import com.example.hong.dto.UserDto;
import com.example.hong.entity.User;
import com.example.hong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    Environment env;

    //모든 계정 조회
    @GetMapping("/")
    public List<User> getUser() {
        return userService.selectUser();
    }

    //신규 계정 생성
    @PostMapping("/new")
    public User postUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    //계정 이름, 비밀번호 수정
    @PutMapping("/{id}")
    public Long updateUser(@PathVariable Long id, @RequestBody UserDto userDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        return userService.updateUser(id, userDto, principalDetail);
    }

    //계정 삭제
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    //무중단 배포 관련
    @GetMapping("/profile")
    public String getProfile () {
        return Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("");
    }
}
