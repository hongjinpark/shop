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

    @Column(nullable = false)
    private String imgUrl; //상품 이미지 경로

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItem=new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<CartItem> cartItem=new ArrayList<>();


    @Builder
    public Item(String imgUrl,String itemName, int price, int stockNumber, String itemDetail, ItemSellStatus itemSellStatus) {
        this.imgUrl=imgUrl;    //이미지경로
        this.itemName = itemName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.itemDetail = itemDetail;
        this.itemSellStatus=itemSellStatus;
    }

    public void updateItem(String itemImg,String itemName, int price, int stockNumber, String itemDetail){
        this.imgUrl=itemImg;    //이미지경로
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
}
