package com.example.hong.dto;


import com.example.hong.constant.ItemSellStatus;
import com.example.hong.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {

    @NotEmpty(message = "상품명은 필수 입력 값입니다.")
    private String itemName; //상품명
    @NotEmpty(message = "가격은 필수 입력 값입니다.")
    private Integer price; //가격
    @NotEmpty(message = "재고수량은 필수 입력 값입니다.")
    @Min(value = 1,message = "최소 재고수량은 1개 입니다. ")
    private int stockNumber; //재고수량
    @NotEmpty(message = "상품 상세 설명은 필수 입력 값입니다.")
    private String itemDetail; //상품 상세 설명

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();
    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem() {

        return modelMapper.map(this, Item.class);
    }

    public static Item of(Item item) {

        return modelMapper.map(item, Item.class);
    }

    public Item toEntity(){
        return Item.builder()
                .itemName(itemName)
                .price(price)
                .stockNumber(stockNumber)
                .itemDetail(itemDetail)
                .itemSellStatus(ItemSellStatus.SELL)
                .build();
    }
}
