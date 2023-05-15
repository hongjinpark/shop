package com.example.hong.repository;

import com.example.hong.dto.ItemDto;
import com.example.hong.dto.MainItemDto;
import com.example.hong.dto.QMainItemDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.hong.entity.QItem.item;
import static com.example.hong.entity.QItemImg.itemImg;


@Repository
@RequiredArgsConstructor
public class ItemCustomRepositoryImpl implements ItemCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MainItemDto> findAllItemAndImgUrl() {
        return queryFactory.select(
                        new QMainItemDto(
                                item.id,
                                item.itemName,
                                item.itemDetail,
                                itemImg.imgUrl,
                                item.price)
                )
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.repImgYn.eq("Y"))
                .orderBy(item.id.desc())
                .fetch();
    }
}
