package com.example.hong.service;


import com.example.hong.dto.OrderItemDto;
import com.example.hong.mapper.OrderMapper;
import com.example.hong.repository.ItemRepository;
import com.example.hong.repository.OrderItemRepository;
import com.example.hong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class OrderService {


    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    //Jpa

    /**
     *
     * @param
     * @return
     */

    public void order(Long item_id, int count, int orderPrice){

        OrderItemDto orderItemDto = orderItemDto.toEntity().b
                .item_id(item_id)
                .orderPrice(orderPrice)
                .count(count)
                .build();

       orderMapper.order(orderItemDto);
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
