package com.example.hong.dto;

import com.example.hong.entity.Category;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class CategoryDto {
    private Long Category_id;
    private String name;
    private String tier;
    private static ModelMapper modelMapper = new ModelMapper();

    public static CategoryDto of(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto,CategoryDto.class);
    }

    public static Category toEntity(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto,Category.class);
    }
}
