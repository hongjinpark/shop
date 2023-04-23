package com.example.hong.controller;


import com.example.hong.config.auth.PrincipalDetail;
import com.example.hong.dto.OrderDto;
import com.example.hong.dto.OrderHistDto;
import com.example.hong.mapper.OrderMapper;
import com.example.hong.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

   /* @GetMapping
    public ResponseEntity getAllOrder() {
        List<OrderItem> result = orderMapper.getAllOrder();
        return new ResponseEntity(result, HttpStatus.OK);
    }*/

   /* @GetMapping("/{id}")
    public ResponseEntity getOrder(@PathVariable Long id) {
        List<Map<String, Object>> result = orderMapper.getOrder(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }*/


    //주문
    @PostMapping
    public ResponseEntity order(@RequestBody @Valid OrderDto orderDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetail principalDetail) {

        if(bindingResult.hasErrors()) {

            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError fieldError : fieldErrors) {

                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Long orderId;
        orderId = orderService.order(orderDto, principalDetail);

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

    //유저 주문 목록 조회
    @GetMapping
    public ResponseEntity orderHist(@AuthenticationPrincipal PrincipalDetail principalDetail) {

        // 미완성
        // email 추후에 수정
        String email = /*principal.getName();*/ "test01@naver.com";

        List<OrderHistDto> orderHistList = orderService.getOrderList(principalDetail);

        return new ResponseEntity(orderHistList, HttpStatus.OK);
    }

    // 주문 취소
    @PostMapping("/cancel/{orderId}")
    public ResponseEntity cancelOrder(@PathVariable Long orderId, Principal principal) {

        orderService.cancelOrder(orderId);
        return new ResponseEntity(orderId, HttpStatus.OK);
    }
    // 주문 조회
    @GetMapping("/{orderId}")
    public ResponseEntity getOrder(@PathVariable Long orderId) {

        OrderDto orderDto = orderService.getOrder(orderId);
        return new ResponseEntity(orderDto, HttpStatus.OK);
    }
    // 주문 수정
    @PostMapping("/modify/{orderId}")
    public ResponseEntity modifyOrder(@PathVariable Long orderId, @RequestBody @Valid OrderDto orderDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {

            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError fieldError : fieldErrors) {

                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        orderService.modifyOrder(orderId, orderDto);
        return new ResponseEntity(orderId, HttpStatus.OK);
    }
    // 주문 삭제
    @DeleteMapping("/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable Long orderId) {

        orderService.deleteOrder(orderId);
        return new ResponseEntity(orderId, HttpStatus.OK);
    }

}
