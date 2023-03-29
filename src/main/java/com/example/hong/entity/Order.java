package com.example.hong.entity;

import com.example.hong.constant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order{
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItem=new ArrayList<>();

    @Builder
    public Order(OrderStatus orderStatus){
        this.orderStatus=orderStatus;
    }
}
