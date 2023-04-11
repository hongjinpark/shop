package com.example.hong.controller;


import com.example.hong.dto.OrderDto;
import com.example.hong.mapper.OrderMapper;
import com.example.hong.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderDto orderDto, BindingResult bindingResult, Principal principal) {

        if(bindingResult.hasErrors()) {

            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError fieldError : fieldErrors) {

                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        // email 추후에 수정
        String email = /*principal.getName();*/ "test";
        Long orderId;
        orderId = orderService.order(orderDto, email);

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

}
