package com.example.hong.repository;

import com.example.hong.dto.CartDetailDto;
import com.example.hong.dto.CartItemDto;

import java.util.List;

public interface CartCustomRepository {
    List<CartDetailDto> findAllCartOfUser(String email);
}
