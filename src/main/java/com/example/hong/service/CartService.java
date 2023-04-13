package com.example.hong.service;


import com.example.hong.dto.BookDto;
import com.example.hong.dto.CartItemDto;
import com.example.hong.entity.*;
import com.example.hong.repository.CartItemRepository;
import com.example.hong.repository.CartRepository;
import com.example.hong.repository.ItemRepository;
import com.example.hong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public void insertCart(CartItemDto cartItemDto,String email){
        Item item=itemRepository.findById(cartItemDto.getItem().getId()).orElseThrow(EntityNotFoundException::new);
        User user=userRepository.findByEmail(email);
        List<CartItem> cartItemList=new ArrayList<>();

        CartItem cartItem=CartItem.builder()
                .item(item)
                .count(cartItemDto.getCount())
                .build();
        cartItemList.add(cartItem);

        Cart cart=Cart.builder()
                .user(user)
                .cartItems(cartItemList)
                .build();
        cartRepository.save(cart);
    }

}
