package com.example.hong.repository;

import com.example.hong.dto.CartItemDto;

import java.util.List;

public interface CartCustomRepository {

    public List<CartItemDto> findAllCartAndUser(int count);
}
