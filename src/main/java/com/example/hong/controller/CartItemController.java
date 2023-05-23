package com.example.hong.controller;

import com.example.hong.config.auth.PrincipalDetail;
import com.example.hong.dto.CartDetailDto;
import com.example.hong.dto.CartItemDto;
import com.example.hong.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartItemController {
    private final CartService cartService;

    @GetMapping("/list")
    public ResponseEntity getCartItemList(@AuthenticationPrincipal PrincipalDetail principalDetail){
        List<CartDetailDto> result = cartService.getCartItemList(principalDetail.getEmail());
        return new ResponseEntity(result, HttpStatus.OK); }

    @PostMapping("/new")
    public ResponseEntity createCart(@RequestBody CartItemDto cartItemDto,
                                     @AuthenticationPrincipal PrincipalDetail principalDetail){
        cartService.createCart(cartItemDto, principalDetail.getEmail());
        return ResponseEntity.ok().build(); }

    @PatchMapping("/list/{id}")
    public ResponseEntity modifyCart(@PathVariable Long id,@RequestParam int count){
        cartService.modifyCart(id, count);
        return ResponseEntity.ok().build(); }

    @DeleteMapping("/list/{id}")
    public ResponseEntity deleteCart(@PathVariable Long id){
        cartService.deleteCart(id);
        return ResponseEntity.ok().build(); }

    @PostMapping("/list/cart-to-order/{id}")
    public ResponseEntity cartToOrder(@PathVariable Long id,
                                      @AuthenticationPrincipal PrincipalDetail principalDetail){
        cartService.cartToOrder(id, principalDetail.getEmail());
        return ResponseEntity.ok().build();}
}
