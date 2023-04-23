package com.example.hong.controller;


import com.example.hong.config.auth.PrincipalDetail;
import com.example.hong.dto.UserDto;
import com.example.hong.entity.User;
import com.example.hong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    Environment env;

    //모든 계정 조회
    @GetMapping
    public List<User> getUser() {
        return userService.selectUser();
    }
    // 계정 조회
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody UserDto userDto) {
        UserDto result = userService.loginUser(userDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //신규 계정 생성 (회원가입)
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


    @GetMapping("/loginInfo")
    public String oauthLoginInfo(Authentication authentication) {
        //oAuth2User.toString() 예시 : Name: [2346930276], Granted Authorities: [[USER]], User Attributes: [{id=2346930276, provider=kakao, name=김준우, email=bababoll@naver.com}]
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        //attributes.toString() 예시 : {id=2346930276, provider=kakao, name=김준우, email=bababoll@naver.com}
        Map<String, Object> attributes = oAuth2User.getAttributes();
        return attributes.toString();
    }


    //무중단 배포 관련
    @GetMapping("/profile")
    public String getProfile () {
        return Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("");
    }
}
