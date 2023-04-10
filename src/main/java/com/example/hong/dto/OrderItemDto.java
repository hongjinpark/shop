package com.example.hong.dto;


import com.example.hong.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Long item_id; //item 아이디
    private int count; //주문 수량
    private int orderPrice; //주문 금액
    private String imgUrl; //상품 이미지 경로

    @Builder
    public OrderItem toEntity(){

        return OrderItem.builder()
                .count(count)
                .orderPrice(orderPrice)
                .build();
    }
}
