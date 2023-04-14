package com.example.hong.repository;

import com.example.hong.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT o.id, o.orderDate, o.orderStatus FROM Order o WHERE o.id = :id")
    List findOrderList(@Param("orderId") Long id);


}
