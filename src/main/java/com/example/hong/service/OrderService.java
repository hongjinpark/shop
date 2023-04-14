package com.example.hong.service;


import com.example.hong.constant.OrderStatus;
import com.example.hong.dto.OrderDto;
import com.example.hong.entity.Item;
import com.example.hong.entity.Order;
import com.example.hong.entity.OrderItem;
import com.example.hong.entity.User;
import com.example.hong.repository.ItemRepository;
import com.example.hong.repository.OrderItemRepository;
import com.example.hong.repository.OrderRepository;
import com.example.hong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    //Jpa
    public Long order(OrderDto orderDto, String email) {

        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        User user = userRepository.findByEmail(email);
        List<OrderItem> orderItemList = new ArrayList<>();

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), orderDto.getCount());
        orderItemList.add(orderItem);

        LocalDateTime orderDate = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDER;


        Order order = Order.createOrder(user, orderDate, orderStatus, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional(readOnly = true)
    public List getOrderList(Long orderId) {

        List orderHistList = orderRepository.findOrderList(orderId);

        return orderHistList;
    }

    //Mybatis
}
