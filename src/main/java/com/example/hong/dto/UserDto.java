package com.example.hong.dto;


import com.example.hong.constant.Role;
import com.example.hong.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String password;

    //일반 사용자 User
    private String provider;

    private Role role;

    private String token;

    public User toEntity(){
        return User.builder()
                .email(email)
                .name(name)
                .address(address)
                .password(password)
                .provider("user")
                .role(Role.USER)
                .build();
    }

    public static UserDto toDto(OAuth2User oAuth2User) {
        var attributes = oAuth2User.getAttributes();

        //google 일때
        if(attributes.get("email") != null) {
            return UserDto.builder()
                    .email((String)attributes.get("email"))
                    .name((String)attributes.get("name"))
                    .build();
        }
        //naver 일때
        else {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");

            return UserDto.builder()
                    .email((String)response.get("email"))
                    .name((String)response.get("name"))
                    .build();
        }
    }

    public UserDto(User user) {
        this.email=user.getEmail();
        this.name=user.getName();
        this.address=user.getAddress();
        this.role=user.getRole();
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
