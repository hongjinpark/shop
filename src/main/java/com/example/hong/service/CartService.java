package com.example.hong.service;


import com.example.hong.dto.BookDto;
import com.example.hong.dto.CartItemDto;
import com.example.hong.entity.Book;
import com.example.hong.entity.Cart;
import com.example.hong.entity.CartItem;
import com.example.hong.entity.Item;
import com.example.hong.repository.CartItemRepository;
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

}
