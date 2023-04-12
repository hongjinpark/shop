package com.example.hong.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter
public class Cart extends BaseEntity {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;


    @Builder
    public static Cart createCart(User user) {

        Cart cart;
        cart = Cart.builder()
                .user(user)
                .build();

        return cart;
    }
}
