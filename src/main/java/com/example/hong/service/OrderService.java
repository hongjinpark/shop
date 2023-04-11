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
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RequiredArgsConstructor
@Service
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



        /*OrderItem orderItem = OrderItem.OrderItem(item, orderDto.getCount());*/
        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .count(orderDto.getCount())
                .orderPrice(item.getPrice())
                .build();
        orderItemList.add(orderItem);

        LocalDateTime orderDate = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDER;


        Order order = Order.createOrder(user, orderDate, orderStatus, orderItemList);
        orderRepository.save(order);
        /*Order order = Order.builder()
                .user(user)
                .orderItems(orderItemList)
                .orderDate(orderDate)
                .orderStatus(orderStatus)
                .build();
        orderRepository.save(order);
*/

        return order.getId();
    }


   /* @Transactional
    public Item updateItem(Long id,ItemDto itemDto){
        Item item=orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));

        item.updateItem(itemDto.getItemName(),itemDto.getPrice(),itemDto.getStockNumber(), itemDto.getImgUrl(), itemDto.getItemDetail());
        return orderRepository.save(item);
    }

    @Transactional
    public void deleteItem(Long id){
        orderRepository.deleteById(id);
    }

    //Mybatis
    public Item selectItem(Long itemId) {
        return orderRepository.findById(itemId).orElseThrow(IllegalArgumentException::new);
    }*/
}
