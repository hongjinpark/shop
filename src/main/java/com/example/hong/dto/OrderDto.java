package com.example.hong.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class OrderDto {

    @NotNull(message = "상품 아이디를 입력 해주세요.")
    private Long itemId;

    private int count;


}
