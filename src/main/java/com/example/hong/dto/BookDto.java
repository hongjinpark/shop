package com.example.hong.dto;


import com.example.hong.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    @NotEmpty(message = "이름은 필수 입력 값입니다.")
    private String name;
    @NotEmpty(message = "제목은 필수 입력 값입니다.")
    private String title;
    @Min(value = 5000, message = "금액은 5000원 이상이어야 합니다.")
    private int price;

    @NotEmpty(message = "저자를 입력하세요.")
    private String publisher;

    //resquest dto 로 받은 Book 객체를 entity 화하여 저장하는 용도
    public Book toEntity(){
        return Book.builder()
                .name(name)
                .title(title)
                .price(price)
                .publisher(publisher)
                .build();
    }
}
