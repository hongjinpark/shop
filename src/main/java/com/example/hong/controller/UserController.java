package com.example.hong.controller;


import com.example.hong.dto.UserDto;
import com.example.hong.entity.User;
import com.example.hong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

    //비밀번호 확인 처리 요청
    @PostMapping("/{id}")
    public boolean checkPw(@PathVariable String id, @RequestBody UserDto userDto) {
        return userService.checkPw(id, userDto);
    }

    //비밀번호 변경
    @PutMapping("/{id}")
    public User updatePw(@PathVariable String id, @RequestBody UserDto userDto) {
        return userService.updatePw(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @GetMapping("/profile")
    public String getProfile () {
        return Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("");
    }
}
