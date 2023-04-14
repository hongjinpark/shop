package com.example.hong.dto;

import com.example.hong.constant.OrderStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderHistDto {

    private Long orderId; //주문 아이디
    private String orderDate; //주문날짜
    private OrderStatus orderStatus; //주문 상태


   /* @Builder
    public OrderHistDto(Long orderId, String orderDate, OrderStatus orderStatus) {

        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }*/

    @Builder
    public OrderHistDto(Long orderId, String orderDate, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }
}
