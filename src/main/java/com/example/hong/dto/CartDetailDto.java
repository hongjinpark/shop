package com.example.hong.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartDetailDto {

    private Long cartItemId;
    private String itemNm;
    private int price;
    private int count;
    private String imgName;

    private Long itemId;

    public CartDetailDto(Long cartItemId, String itemNm, int price, int count, String imgName) {
        this.cartItemId = cartItemId;
        this.itemNm = itemNm;
        this.price = price;
        this.count = count;
        this.imgName = imgName;
    }

}