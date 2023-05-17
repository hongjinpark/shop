package com.example.hong.repository;

import com.example.hong.dto.CartDetailDto;
import com.example.hong.dto.CartItemDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.hong.entity.QCart.cart;
import static com.example.hong.entity.QCartItem.cartItem;
import static com.example.hong.entity.QItem.item;
import static com.example.hong.entity.QItemImg.itemImg;
import static com.example.hong.entity.QUser.user;


@Repository
@RequiredArgsConstructor
public class CartCustomRepositoryImpl implements CartCustomRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CartDetailDto> findAllCartOfUser(String email) {
        return queryFactory.select(Projections.fields(CartDetailDto.class,
                        cartItem.id.as("cartItemId"),
                        cartItem.count.as("count"),
                        item.itemName.as("itemNm"),
                        item.price.as("price"),
                        itemImg.imgName.as("imgName")))
                .from(cartItem)
                .innerJoin(cartItem.cart,cart)
                .innerJoin(cartItem.item,item)
                .leftJoin(itemImg).on(itemImg.item.id.eq(cartItem.item.id))
                .leftJoin(cart.user,user)
                .where(user.email.eq(email),itemImg.repImgYn.eq("Y"))
                .orderBy(cartItem.regTime.desc())
                .fetch();
    }
}
