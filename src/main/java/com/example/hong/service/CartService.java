package com.example.hong.service;


import com.example.hong.constant.OrderStatus;
import com.example.hong.dto.CartItemDto;
import com.example.hong.entity.*;
import com.example.hong.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;





    @Transactional
    public void insertCart(CartItemDto cartItemDto,String email){

        Item item=itemRepository.findById(cartItemDto.getItemId()).orElseThrow(EntityNotFoundException::new);
        User user=userRepository.findByEmail(email);
        List<CartItem> cartItemList=new ArrayList<>();

        CartItem Item=CartItem.builder()
                .item(item)
                .count(cartItemDto.getCount())
                .build();
        cartItemList.add(Item);

        Cart cart=Cart.builder()
                .user(user)
                .cartItems(cartItemList)
                .build();

        Item.setCart(cart); //연관관계 편의 메서드

        cartRepository.save(cart);
    }

    @Transactional
    public List<CartItemDto> findAllCartAndUser(int count) {
        return cartRepository.findAllCartAndUser(count);
    }

    @Transactional
    public void cartToOrder(Long cartId,String email){
        CartItem cartItem=cartItemRepository.findByCart_id(cartId);
        Item item=itemRepository.findById(cartItem.getItem().getId()).get();
        User user=userRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), cartItem.getCount());
        orderItemList.add(orderItem);

        LocalDateTime orderDate = LocalDateTime.now();
        OrderStatus orderStatus = OrderStatus.ORDER;

        Order order = Order.createOrder(user, orderDate, orderStatus, orderItemList);
        orderRepository.save(order);
        cartRepository.deleteById(cartItem.getCart().getId());
    }
    @Transactional
    public void updateCart(Long id,int count){
        CartItem cartItem=cartItemRepository.findByCart_id(id);
        cartItem.updateCount(count);}
    @Transactional
    public void deleteCart(Long id){
        cartRepository.deleteById(id);
    }


    //    개인 cartitemlist
    public List<CartItemDto> getCartItemList(String email){
        User user = userRepository.findByEmail(email);
        return cartRepository.findAllCartOfUser(user.getId());
    }
    //    개인 cartitem
    public CartItemDto getCartItem(String email,Long id){
        User user=userRepository.findByEmail(email);
        return cartRepository.findCartOfUser(user.getId(),id);
    }
}
