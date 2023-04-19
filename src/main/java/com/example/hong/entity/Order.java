package com.example.hong.entity;

import com.example.hong.constant.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "orders")

public class Order extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    private LocalDateTime orderDate; //주문일
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; //주문상태


    @OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    private Order(User user, List<OrderItem> orderItems, LocalDateTime orderDate, OrderStatus orderStatus) {

        this.user = user;
        this.orderItems = orderItems;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;

        // 연관관계 편의 메소드
        /*OrderItem.builder().order(this).build();*/

    }

    @Builder
    public static Order createOrder(User user, LocalDateTime orderDate, OrderStatus orderStatus, List<OrderItem> orderItems) {

            Order order = Order.builder()
                .user(user)
                .orderDate(orderDate)
                .orderStatus(orderStatus)
                .orderItems(new ArrayList<>())
                .build();

        for (OrderItem orderItem : orderItems) {

            order.addOrderItem(orderItem);
        }

        return order;
    }

    public void addOrderItem(OrderItem orderItem) {

        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void cancelOrder() {

        this.orderStatus = OrderStatus.ORDER.CANCEL;

        for(OrderItem orderItem : orderItems) {

            orderItem.cancel();
        }
    }
}
