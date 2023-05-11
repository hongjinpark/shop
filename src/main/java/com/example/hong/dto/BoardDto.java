package com.example.hong.dto;

import com.example.hong.constant.QuestionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

    private Long id;
    private String name;

    @NotEmpty(message = "제목은 필수 입력 값입니다.")
    @Length(max=20, message = "제목은 20자 이하로 작성해주세요.")
    private String title;

    @NotEmpty(message = "내용은 필수 입력 값입니다.")
    private String content;

    private QuestionStatus questionStatus;

    private String email;

//    public Board toEntity(){
//        return Board.builder()
//                .name(name)
//                .title(title)
//                .content(content)
//                .user(user)
//                .questionStatus(QuestionStatus.WAIT)
//                .build();
//
//    }
}
