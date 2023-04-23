package com.example.hong.controller;

import com.example.hong.config.auth.PrincipalDetail;
import com.example.hong.dto.CartItemDto;
import com.example.hong.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartItemController {
    private final CartService cartService;

    @GetMapping("/list")
    public ResponseEntity getCartItemList(@AuthenticationPrincipal PrincipalDetail principalDetail){
        List<CartItemDto> result = cartService.getCartItemList(principalDetail.getEmail());
        return new ResponseEntity(result, HttpStatus.OK); }

    // principalDetail.getEmail() : 로그인한 사용자의 이메일
    // 로그인한 사용자의 이메일을 통해 장바구니 테이블에서 해당 사용자의 장바구니 목록을 가져온다.
    // 장바구니 목록은 CartItemDto로 이루어진 List이다.
    // CartItemDto는 장바구니 테이블과 상품 테이블의 데이터를 조합한 데이터이다.
    @GetMapping("/list/total-price") // 총 가격
    public ResponseEntity getTotalPrice(@AuthenticationPrincipal PrincipalDetail principalDetail){
        int result = cartService.getTotalPrice(principalDetail.getEmail());
        return new ResponseEntity(result, HttpStatus.OK); }

    @GetMapping("/list/{id}")   //id값은 cartItemId
    public ResponseEntity getCartItem(@PathVariable Long id,@AuthenticationPrincipal PrincipalDetail principalDetail){
        CartItemDto result=cartService.getCartItem(principalDetail.getEmail(), id);
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

    @DeleteMapping("/list/{id}")//id값은 cartId
    public ResponseEntity deleteCart(@PathVariable Long id){
        cartService.deleteCart(id);
        return ResponseEntity.ok().build(); }

    @PostMapping("/list/{id}/cart-to-order")
    public ResponseEntity cartToOrder(@PathVariable Long id,
                                      @AuthenticationPrincipal PrincipalDetail principalDetail){
        cartService.cartToOrder(id, principalDetail.getEmail());
        return ResponseEntity.ok().build();}
    // 장바구니에서 주문하기 버튼을 누르면 주문 테이블에 데이터가 추가되고 장바구니 테이블에서 삭제된다.
    // 주문 테이블에 데이터가 추가되는 것은 orderService에서 처리한다.
    // 장바구니 테이블에서 삭제하는 것은 cartService에서 처리한다.

}
