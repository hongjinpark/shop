package com.example.hong.entity;


import com.example.hong.constant.ItemSellStatus;
import com.example.hong.controller.GlobalExceptionHandler;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item extends BaseEntity{

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //상품 코드

    @Column(nullable = false, length = 50)
    private String itemName; //상품명

    @Column(name = "price", nullable = false)
    private int price; //가격

    @Column(nullable = false)
    private int stockNumber; //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItem=new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<CartItem> cartItem=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name ="category_id")
    private Category category;

    @Builder
    public Item(String itemName, int price, int stockNumber, String itemDetail, ItemSellStatus itemSellStatus, Category category) {
        this.itemName = itemName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.itemDetail = itemDetail;
        this.itemSellStatus=itemSellStatus;
        this.category=category;
    }

    public void updateItem(String itemName, int price, int stockNumber, String itemDetail){
        this.itemName=itemName;
        this.price=price;
        this.stockNumber=stockNumber;
        this.itemDetail=itemDetail;
    }

    public void removeStock(int stockNumber) {

        int restStock = this.stockNumber - stockNumber;
        if(restStock < 0) {

            throw new GlobalExceptionHandler.OutOfStockException("상품의 재고가 부족합니다.(현재 재고 수량: "+ this.stockNumber +")");
        }
        this.stockNumber = restStock;
    }

    public void addStock(int stockNumber) {

        this.stockNumber += stockNumber;
    }
}
