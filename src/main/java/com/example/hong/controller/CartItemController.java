package com.example.hong.controller;

import com.example.hong.dto.CartItemDto;
import com.example.hong.entity.Cart;
import com.example.hong.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartItemController {

    private final CartService cartService;

    @PostMapping("/new")
    public ResponseEntity postCart(@RequestBody CartItemDto cartItemDto, Principal principal){
        String email="test01@naver.com";
        cartService.insertCart(cartItemDto,email);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity getCartItem(@PathVariable Long id, Principal principal){
//    }
//    @GetMapping("/list")
//    public ResponseEntity getCartItemList(Principal principal){
//    }
//    @GetMapping("/{id}/cart-to-order")
//    public ResponseEntity cartToOrder(@PathVariable Long id,Principal principal){
//        return ResponseEntity.ok().build();}

    @PatchMapping("/{id}")
    public ResponseEntity updateCart(@PathVariable Long id,@RequestParam int count){
        cartService.updateCart(id, count);
        return ResponseEntity.ok().build(); }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCart(@PathVariable Long id){
        cartService.deleteCart(id);
        return ResponseEntity.ok().build(); }



}
