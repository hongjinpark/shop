package com.example.hong.repository;

import com.example.hong.dto.CartItemDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.hong.entity.QCartItem.cartItem;
import static com.example.hong.entity.QItem.item;

@Repository
@RequiredArgsConstructor
public class CartCustomRepositoryImpl implements CartCustomRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CartItemDto> findAllCartAndUser(int count) {
        return queryFactory.select(Projections.fields(CartItemDto.class,
                cartItem.count,
                item.id))
                .from(cartItem)
                .innerJoin(cartItem.item, item)
                .where(cartItem.count.gt(count))
                .fetch();
    }

}
