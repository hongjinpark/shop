package com.example.hong.controller;

import com.example.hong.dto.CartItemDto;
import com.example.hong.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartItemController {

    private final CartService cartService;

    @PostMapping("/new")
    public ResponseEntity postCart(@RequestBody CartItemDto cartItemDto){
        String email="test01@naver.com";
        cartService.insertCart(cartItemDto,email);
        return ResponseEntity.ok().build();
    }
}
