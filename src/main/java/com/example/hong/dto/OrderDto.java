package com.example.hong.dto;

import com.example.hong.entity.Book;
import com.example.hong.entity.Order;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class OrderDto {

    @NotNull(message = "상품 아이디를 입렵해주세요.")
    private Long itemId;

    private int count;


}
