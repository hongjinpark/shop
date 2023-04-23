package com.example.hong.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface RoomMapper {
    Map<String, Object> getAllRoom();
}
