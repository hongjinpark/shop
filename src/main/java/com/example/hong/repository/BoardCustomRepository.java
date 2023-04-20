package com.example.hong.repository;

import com.example.hong.dto.BoardDto;
import com.example.hong.entity.Board;

import java.util.List;

public interface BoardCustomRepository {
    BoardDto findBoardByName(Long id,String email);
    List<BoardDto> findBoardAllByEmailIndividual(String email);
}
