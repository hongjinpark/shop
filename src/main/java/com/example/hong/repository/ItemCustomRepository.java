package com.example.hong.repository;

import com.example.hong.dto.MainItemDto;

import java.util.List;

public interface ItemCustomRepository {
    List<MainItemDto> findAllItemAndImgUrl();
}
