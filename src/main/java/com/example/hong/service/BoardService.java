package com.example.hong.service;

import com.example.hong.config.auth.PrincipalDetail;
import com.example.hong.constant.QuestionStatus;
import com.example.hong.constant.Role;
import com.example.hong.dto.BoardDto;
import com.example.hong.entity.Board;
import com.example.hong.entity.User;
import com.example.hong.repository.BoardRepository;
import com.example.hong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public String inputAnswer(Long id,String email,String answer){
        Board board=boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 id가 없습니다. id=" + id));

        if (isUserRole(email)){
            board.insertAnswer(answer);
            boardRepository.save(board);
            return board.getAnswer();
        }
        return null;
    }

    @Transactional
    public void deleteBoard(Long id){
        boardRepository.deleteById(id);
    }

    public List<BoardDto> getBoardListOfUser(@AuthenticationPrincipal PrincipalDetail principalDetail){
        return boardRepository.findBoardAllByEmailOfUser(principalDetail.getEmail()); }

    public BoardDto getBoardOfUser(Long id,String email){
        return boardRepository.findBoardByEmailOfUser(id,email); }

    public BoardDto getBoardOfAdmin(Long id,String email){
        return boardRepository.findBoardByEmailOfAdmin(id,email); }

    public List<BoardDto> getBoardListOfAdmin(){
        return boardRepository.findBoardAllByEmailOfAdmin(); }

    public boolean isUserRole(String email){
        User user=userRepository.findByEmail(email);
        return user.getRole().equals(Role.ADMIN); }
}
