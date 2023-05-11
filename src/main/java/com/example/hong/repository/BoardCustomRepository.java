package com.example.hong.repository;

import com.example.hong.dto.BoardDto;
import com.example.hong.entity.Board;

import java.util.List;

public interface BoardCustomRepository {
    BoardDto findBoardByEmailOfUser(Long id,String email);
    BoardDto findBoardByEmailOfAdmin(Long id,String email);
    List<BoardDto> findBoardAllByEmailOfUser(String email);
    List<BoardDto> findBoardAllByEmailOfAdmin();
}
