package com.example.hong.service;

import com.example.hong.constant.QuestionStatus;
import com.example.hong.constant.Role;
import com.example.hong.dto.BoardDto;
import com.example.hong.entity.Board;
import com.example.hong.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Board createBoard(BoardDto boardDto){
        Board board=boardDto.toEntity();
        return boardRepository.save(board);
    }

    public List<Board> getListBoard(){ //전체 문의 조회(관리자용)
        return boardRepository.findAll();
    }

    public List<Board> getIndividualBoard(String name){   //문의 조회(사용자용)
        return boardRepository.findAllByName(name);
    }

//    public Board insertAnswer(Long id,String answer){     //관리자만 댓글가능하게
//        Board board=boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 id가 없습니다. id=" + id));
//        if(Role.USER==)
//        board.insertAnswer(answer);
//    }

}
