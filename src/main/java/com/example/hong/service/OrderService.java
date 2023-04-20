package com.example.hong.service;


import com.example.hong.config.auth.PrincipalDetail;
import com.example.hong.constant.OrderStatus;
import com.example.hong.dto.OrderDto;
import com.example.hong.dto.OrderHistDto;
import com.example.hong.dto.OrderItemDto;
import com.example.hong.entity.*;
import com.example.hong.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {


    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ItemImgRepository itemImgRepository;

    //Jpa

    //주문
    public Long order(OrderDto orderDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {

        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        User user = userRepository.findByEmail(principalDetail.getEmail());
        List<OrderItem> orderItemList = new ArrayList<>();

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), orderDto.getCount());
        orderItemList.add(orderItem);

        LocalDateTime orderDate = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDER;


        Order order = Order.createOrder(user, orderDate, orderStatus, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }

    //주문 목록 조회
    @Transactional(readOnly = true)
    public List<OrderHistDto> getOrderList(PrincipalDetail principalDetail) {

        List<Order> orders = orderRepository.findOrders(principalDetail.getEmail());

        List<OrderHistDto> orderHistDtos = new ArrayList<>();

        for (Order order : orders) {

            OrderHistDto orderHistDto = new OrderHistDto(order);
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {

                ItemImg itemImg = itemImgRepository.findByItemIdAndRepImgYn(orderItem.getItem().getId(), "Y");
                OrderItemDto orderItemDto = new OrderItemDto(orderItem, itemImg.getImgUrl());
                orderHistDto.addOrderItemDto(orderItemDto);
            }

            orderHistDtos.add(orderHistDto);
        }

        return orderHistDtos;
    }

    public void cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        order.cancelOrder();
    }

    //Mybatis
}
