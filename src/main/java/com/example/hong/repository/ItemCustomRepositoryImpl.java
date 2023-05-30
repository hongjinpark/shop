package com.example.hong.repository;

import com.example.hong.dto.ItemSearchDto;
import com.example.hong.dto.MainItemDto;
import com.example.hong.dto.QMainItemDto;
import com.example.hong.entity.QItem;
import com.example.hong.entity.QItemImg;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ItemCustomRepositoryImpl implements ItemCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MainItemDto> findAllItemAndImgUrl(ItemSearchDto itemSearchDto) {

        QItem item = QItem.item;
        QItemImg itemImg = QItemImg.itemImg;

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
                .where(itemNameLike(itemSearchDto.getSearchQuery()))
                .orderBy(item.id.desc())
                .fetch();
    }

    private BooleanExpression itemNameLike(String searchQuery) {

        return StringUtils.hasText(searchQuery) ? QItem.item.itemName.like("%" + searchQuery + "%") : null;
    }
}
