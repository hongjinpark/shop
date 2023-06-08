package com.example.hong.dto;


import com.example.hong.entity.CartItem;
import com.example.hong.entity.Item;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {


    private Long itemId;


    private int count;

    private Long cartItemId;
    private Long userId;
    private Item item;

    @Builder
   public CartItem toEntity(Item item, int count){
        return CartItem.builder()
                .item(item)
                .count(count)
                .build();
    }
}