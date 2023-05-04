package com.example.hong.service;


import com.example.hong.config.JwtProvider;
import com.example.hong.config.auth.PrincipalDetail;
import com.example.hong.dto.UserDto;
import com.example.hong.entity.User;
import com.example.hong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;


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
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + id));

        user.update(passwordEncoder.encode(userDto.getPassword()), userDto.getName());

        userRepository.save(user);

        if(principalDetail != null) {
            principalDetail.setUser(user); //추가
        }

        return user.getId();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    //jwt 토큰 값 가져오기
  /*  private String getJwtToken(User user, PrincipalDetail principalDetail) {
        return jwtProvider.createToken(user.getName(), principalDetail.getAuthorities()
                .stream().map(Object::toString).collect(Collectors.toList()));
    }*/

    private void validateDuplicateMember(User user) {

        User findUser = userRepository.findByEmail(user.getEmail());
        if(findUser != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public UserDto loginUser(UserDto userDto) {

        User user = userRepository.findByEmail(userDto.getEmail());
        if(user == null) {
            throw new BadCredentialsException("잘못된 계정정보입니다.");

        }

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("잘못된 계정정보입니다.");
        }

        /*User result = userRepository.findUser(userDto.getEmail());*/

        UserDto result = UserDto.builder()
                .id(user.getId())
                .address(user.getAddress())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .provider("user")
                .role(user.getRole())
                .token(jwtProvider.createToken(user.getEmail(), user.getRole()))
                .build();

        return result;
    }

    public User getUser(@AuthenticationPrincipal PrincipalDetail principalDetail) {

        User userInfo = userRepository.findUser(principalDetail.getEmail());

        if(userInfo == null) {

            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. ");
        }
        return userInfo;
    }
}
