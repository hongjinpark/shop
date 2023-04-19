package com.example.hong.dto;

import com.example.hong.constant.OrderStatus;
import com.example.hong.entity.Order;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderHistDto {

    private Long orderId; //주문 아이디
    private String orderDate; //주문날짜
    private OrderStatus orderStatus; //주문 상태

    //주문 상품 리스트
    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

   /* @Builder
    public OrderHistDto(Long orderId, String orderDate, OrderStatus orderStatus) {

        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }*/

    public OrderHistDto(Order order) {

        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }

    public void addOrderItemDto(OrderItemDto orderItemDto) {

        orderItemDtoList.add(orderItemDto);
    }
}
