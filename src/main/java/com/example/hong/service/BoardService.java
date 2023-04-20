package com.example.hong.service;

import com.example.hong.constant.QuestionStatus;
import com.example.hong.constant.Role;
import com.example.hong.dto.BoardDto;
import com.example.hong.entity.Board;
import com.example.hong.entity.User;
import com.example.hong.repository.BoardRepository;
import com.example.hong.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Transactional
    public Board createBoard(BoardDto boardDto,User userId){
//        Board board=boardDto.toEntity();
        Board board= Board.builder()
                .title(boardDto.getTitle())
                .name(boardDto.getName())
                .content(boardDto.getContent())
                .questionStatus(QuestionStatus.WAIT)
                .user(userId)
                .build();
        return boardRepository.save(board);
    }

    public List<Board> getBoardList(){ //전체 문의 조회(관리자용)
        return boardRepository.findAllByOrderByIdDesc();
    }   //관리자용(문의 전체 리스트)

    public BoardDto getBoard(Long id,String email){ //querydsl처리 관리자용 1개 문의 조회
        return boardRepository.findBoardByName(id,email);
    }

    public List<BoardDto> getIndividualBoard(String email){   //문의 조회(사용자용)
        return boardRepository.findBoardAllByEmailIndividual(email);
    }   //유저용(자신 문의내용 전체 불러오기)

    @Transactional
    public Board insertAnswer(Long id,String email,String answer){     //관리자만 문의 답장가능하게
        Board board=boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 id가 없습니다. id=" + id));
        if (checkUserRole(email)){
            board.insertAnswer(answer);
            return boardRepository.save(board);
        }else {
         //오류
         log.info("오류구현");
         return null;
        }
    }

    @Transactional
    public void deleteBoard(Long id){
        boardRepository.deleteById(id);
    }

    public boolean checkUserRole(String email){
        User user=userRepository.findByEmail(email);
        return user.getRole().equals(Role.ADMIN);
    }

}
