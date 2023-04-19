package com.example.hong.repository;

import com.example.hong.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT o FROM Order o WHERE o.user.email = :email ORDER BY o.orderDate DESC")
    List findOrders(@Param("email") String email);

    @Query(value = "select count(o) from Order o where o.user.email = :email")
    Long countOrder(@Param("email") String email);
}
