package com.example.hong.controller;

import com.example.hong.dto.BoardDto;
import com.example.hong.entity.Board;
import com.example.hong.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;


    @GetMapping("/user/my-board") //개인 문의 리스트(유저 자기 자신 것)
    public List<Board> getBoardList() {
    String name="ggurals";
        return boardService.getIndividualBoard(name);}

    @GetMapping("/user/my-board/{id}") //개인 문의(유저 자기 자신 것)
    public Board getBoard(@PathVariable Long id){
        return boardService.getBoard(id);
    }

    @PostMapping("/user/new")    //유저 문의 create
    public Board postBoard(@RequestBody BoardDto boardDto){
        return boardService.createBoard(boardDto); }

    @DeleteMapping("/user/my-board/{id}")
    public void deleteBoard(@PathVariable Long id){
        boardService.deleteBoard(id);
    }

    @GetMapping("/admin")      // 유저 전체문의 보기(관리자용)
    public List<Board> getAdminBoardList(){
        return boardService.getBoardList();
    }

    @GetMapping("/admin/{id}")  //유저 개인문의 보기(관리자용)
    public Board getAdminBoard(@PathVariable Long id){
        return boardService.getBoard(id);
    }

    @PostMapping("/admin/answer/{id}")  //문의 답장(관리자)
    public Board postAnswer(@PathVariable Long id,@RequestBody String answer){
        String emailtest="test1@naver.com";
        return boardService.insertAnswer(id,emailtest,answer);
    }



}
