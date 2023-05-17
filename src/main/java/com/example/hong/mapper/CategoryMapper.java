package com.example.hong.mapper;


import com.example.hong.dto.CategoryDto;
import com.example.hong.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CategoryMapper {
    void createCategory(CategoryDto categoryDto);


    Map<String, Object> getCategoryList();


    List<Category> getCategory(String tier);
}
