package com.example.hong.dto;

import com.example.hong.entity.Book;
import com.example.hong.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {

    private String itemName; //상품명
    private Integer price; //가격
    private int stockNumber; //재고수량
    private String itemDetail; //상품 상세 설명

    public Item toEntity(){
        return Item.builder()
                .itemName(itemName)
                .price(price)
                .stockNumber(stockNumber)
                .itemDetail(itemDetail)
                .build();
    }
}
