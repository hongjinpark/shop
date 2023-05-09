package com.example.hong.mapper;


import com.example.hong.dto.ItemDto;
import com.example.hong.entity.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {

    List<Item> getAllItem();


}
