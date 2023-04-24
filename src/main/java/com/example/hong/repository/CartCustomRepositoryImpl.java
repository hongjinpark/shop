package com.example.hong.repository;

import com.example.hong.dto.CartDetailDto;
import com.example.hong.dto.CartItemDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.hong.entity.QCartItem.cartItem;
import static com.example.hong.entity.QItem.item;
import static com.example.hong.entity.QItemImg.itemImg;


@Repository
@RequiredArgsConstructor
public class CartCustomRepositoryImpl implements CartCustomRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CartDetailDto> findAllCartOfUser(String email) {
        return queryFactory.select(Projections.fields(CartDetailDto.class,
                        cartItem.id.as("cartItemId"),
                        item.itemName.as("itemNm"),
                        item.price.as("price"),
                        cartItem.count.as("count"),
                        itemImg.imgUrl.as("imgUrl")))
                .from(cartItem,itemImg)
                .join(cartItem.item, item)
                .where(cartItem.cart.user.email.eq(email)
                        .and(itemImg.item.id.eq(cartItem.item.id))
                        .and(itemImg.repImgYn.eq("Y")))
                .orderBy(cartItem.regTime.desc())
                .fetch(); }
}
