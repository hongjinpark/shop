package com.example.hong.mapper;

import com.example.hong.dto.OrderItemDto;
import com.example.hong.entity.Order;
import com.example.hong.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    // 특정 주문 목록 조회
    List<Order> getOrder();

    // 주문 목록 조회
    List<OrderItem> getAllOrder();

    void order(@Valid OrderItemDto orderItemDto);
}
