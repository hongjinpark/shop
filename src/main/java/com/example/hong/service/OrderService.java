package com.example.hong.service;


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
import java.util.ArrayList;
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
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(user, orderItemList);
        orderRepository.save(order);

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
