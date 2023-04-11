package com.example.hong.dto;

import com.example.hong.constant.QuestionStatus;
import com.example.hong.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

    private String name;

    @NotEmpty(message = "제목은 필수 입력 값입니다.")
    private String title;

    @NotEmpty(message = "내용은 필수 입력 값입니다.")
    private String content;

    private QuestionStatus questionStatus;

    public Board toEntity(){
        return Board.builder()
                .name(name)
                .title(title)
                .content(content)
                .questionStatus(QuestionStatus.WAIT)
                .build();

    }
}