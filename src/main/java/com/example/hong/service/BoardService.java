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
        Board board= Board.builder()
                .title(boardDto.getTitle())
                .name(userId.getName())
                .content(boardDto.getContent())
                .questionStatus(QuestionStatus.WAIT)
                .user(userId)
                .build();
        return boardRepository.save(board); }

    @Transactional
    public Board inputAnswer(Long id,String email,String answer){     //관리자만 문의 답장가능하게
        Board board=boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 id가 없습니다. id=" + id));
        if (isUserRole(email)){
            board.insertAnswer(answer);
            return boardRepository.save(board);
        }else{
            //오류
            log.info("오류구현");
            return null;
        }
    }

    @Transactional
    public void deleteBoard(Long id){
        boardRepository.deleteById(id);
    }

    public List<BoardDto> getBoardListOfUser(String email){     //문의 조회(사용자용)
        return boardRepository.findBoardAllByEmailOfUser(email); }

    public BoardDto getBoardOfUser(Long id,String email){ //querydsl처리 관리자용 1개 문의 조회
        return boardRepository.findBoardByEmailOfUser(id,email); }

    public List<BoardDto> getBoardListOfAdmin(){ //전체 문의 조회(관리자용)
        return boardRepository.findBoardAllByEmailOfAdmin(); }

    public boolean isUserRole(String email){
        User user=userRepository.findByEmail(email);
        return user.getRole().equals(Role.ADMIN); }
}
