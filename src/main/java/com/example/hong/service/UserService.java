package com.example.hong.service;


import com.example.hong.config.JwtTokenProvider;
import com.example.hong.config.auth.PrincipalDetail;
import com.example.hong.dto.UserDto;
import com.example.hong.entity.User;
import com.example.hong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;


    public List<User> selectUser() {
        return userRepository.findAll();
    }

    public User createUser(UserDto userDto) {

        userDto.encodePassword(passwordEncoder);
        User user = userDto.toEntity();

        //이메일 중복 검사
        validateDuplicateMember(user);
        return userRepository.save(user);

    }

    @Transactional
    public Long updateUser(Long id, UserDto userDto, PrincipalDetail principalDetail) {
        userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + id));

        User user = userDto.toEntity();
        userDto.encodePassword(passwordEncoder);

        principalDetail.setUser(user); //추가
        return user.getId();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    //jwt 토큰 값 가져오기
    private String getJwtToken(User user, PrincipalDetail principalDetail) {
        return jwtTokenProvider.createToken(user.getName(), principalDetail.getAuthorities()
                .stream().map(Object::toString).collect(Collectors.toList()));
    }

    private void validateDuplicateMember(User user) {

        User findUser = userRepository.findByEmail(user.getEmail());
        if(findUser != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
