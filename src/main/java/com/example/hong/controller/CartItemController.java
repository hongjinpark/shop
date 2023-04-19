package com.example.hong.controller;

import com.example.hong.dto.CartItemDto;
import com.example.hong.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartItemController {

    private final CartService cartService;

    @GetMapping("/cartCount")
    public ResponseEntity cartAndUser(@RequestParam int count) {

        List<CartItemDto> result = cartService.findAllCartAndUser(count);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity postCart(@RequestBody CartItemDto cartItemDto, Principal principal){
        String email="test01@naver.com";
        cartService.insertCart(cartItemDto,email);
        return ResponseEntity.ok().build();
    }



    @PostMapping("/{id}/cart-to-order")
    public ResponseEntity cartToOrder(@PathVariable Long id,Principal principal){
        String email="test01@naver.com";
        cartService.cartToOrder(id,email);
        return ResponseEntity.ok().build();}

    @PatchMapping("/{id}")
    public ResponseEntity updateCart(@PathVariable Long id,@RequestParam int count){
        cartService.updateCart(id, count);
        return ResponseEntity.ok().build(); }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCart(@PathVariable Long id){
        cartService.deleteCart(id);
        return ResponseEntity.ok().build(); }


    @GetMapping("/list")
    public ResponseEntity getCartItemList(Principal principal){
        String email ="test01@naver.com";
        List<CartItemDto> result = cartService.getCartItemList(email);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity getCartItem(@PathVariable Long id, Principal principal){
        String email ="test01@naver.com";
        CartItemDto result=cartService.getCartItem(email,id);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
