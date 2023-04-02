package com.example.hong.dto;

import com.example.hong.entity.Cart;
import com.example.hong.entity.CartItem;
import com.example.hong.entity.Item;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {

    @NotNull(message = "상품 아이디는 필수 입력 값 입니다.")
    private Long itemId;

    @Min(value = 1, message = "최소 1개 이상 담아주세요")
    private int count;

  /*  public CartItem toEntity(Cart cart, Item item, int count){
        return CartItem.builder()
                .cart.getUser(item)
                .item(itemId)
                .count(count)
                .build();
    }*/
}