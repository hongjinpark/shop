package com.example.hong.controller;


import com.example.hong.mapper.OrderMapper;
import com.example.hong.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    public ResponseEntity save(@PathVariable Long item_id, @RequestParam int count, @RequestParam int orderPrice) {


        try {
            orderService.order(item_id, count, orderPrice);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

 /*   @PutMapping("/{id}")
    public Book update(@PathVariable Long id , @RequestBody BookDto bookDto, BindingResult bindingResult) {
        return orderService.update(id, bookDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }*/
}
