package com.example.hong.service;


import com.example.hong.dto.UserDto;
import com.example.hong.entity.User;
import com.example.hong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public List<User> selectUser() {
        return userRepository.findAll();
    }

    public User createUser(UserDto userDto) {

        userDto.encodePassword(passwordEncoder);
        User user = userDto.toEntity();
        return userRepository.save(user);

    }

    public boolean checkPw(String id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));
        boolean flag;

        if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {

            log.info("password = {}", userDto.getPassword());
            log.info("비밀번호 일치");

            flag = true;
        } else {

            log.info("password = {}", userDto.getPassword());
            log.info("비밀번호 불일치");

            flag = false;
        }

        return flag;
    }

    public User updatePw(String id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));

        //평문 비밀번호
        log.info(userDto.getPassword());
        userDto.encodePassword(passwordEncoder);
        user.updatePw(userDto.getPassword());

        //암호화된 비밀번호
        log.info(userDto.getPassword());

        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
