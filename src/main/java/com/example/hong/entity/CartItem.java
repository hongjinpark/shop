package com.example.hong.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name="cart_item")
public class CartItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    @Builder
    public CartItem(Cart cart,Item item,int count){
           this.cart=cart;
           this.item=item;
           this.count=count;
    }

    public void setCart(Cart cart){ //연관관계 편의 메서드
        this.cart=cart;
        cart.getCartItems().add(this);
    }

    public void addCount(int count) { this.count += count; }

    public void updateCount(int count) { this.count = count; }
}
