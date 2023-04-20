package com.example.hong.repository;

import com.example.hong.dto.CartItemDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.hong.entity.QCart.cart;
import static com.example.hong.entity.QCartItem.cartItem;
import static com.example.hong.entity.QItem.item;
import static com.example.hong.entity.QUser.user;


@Repository
@RequiredArgsConstructor
public class CartCustomRepositoryImpl implements CartCustomRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CartItemDto> findAllCartOfUser(Long id) {
        return queryFactory.select(Projections.fields(CartItemDto.class,
                        cartItem.id.as("cartItemId"),
                        cartItem.count,
                        cartItem.item.id.as("itemId"),
                        cartItem.cart.user.id.as("userId")))
                .from(cartItem)
                .join(cartItem.item, item)
                .where(cartItem.cart.user.id.eq(id))
                .fetch(); }
    @Override
    public CartItemDto findCartOfUser(Long userId,Long cartItemId){
        return queryFactory.select(Projections.fields(CartItemDto.class,
                        cartItem.id.as("cartItemId"),
                        cartItem.count,
                        cartItem.item.id.as("itemId"),
                        cartItem.cart.user.id.as("userId")))
                .from(cartItem)
                .join(cartItem.item, item)
                .where(cartItem.cart.user.id.eq(userId).and(cartItem.id.eq(cartItemId)))
                .fetchOne();}
}
