package com.example.hong.mapper;


import com.example.hong.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<Board> searchBoard(String title);
}
