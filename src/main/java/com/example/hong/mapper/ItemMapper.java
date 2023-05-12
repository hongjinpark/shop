package com.example.hong.mapper;


import com.example.hong.entity.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ItemMapper {

    List<Map<String, Object>> getAllItem();


    List<Item> searchItem(String itemName);
}
