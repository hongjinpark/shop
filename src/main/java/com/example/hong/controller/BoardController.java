package com.example.hong.controller;

import com.example.hong.dto.BoardDto;
import com.example.hong.entity.Board;
import com.example.hong.repository.UserRepository;
import com.example.hong.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final UserRepository userRepository;

    @GetMapping("/user/my-board") //개인 문의 리스트(유저 자기 자신 것)
    public ResponseEntity<List<BoardDto>> getBoardList() {
    String email="test01@naver.com"; /*principal.getName();*/
        List<BoardDto> board = boardService.getIndividualBoard(email);
        return new ResponseEntity<List<BoardDto>>(board, HttpStatus.OK);}

    @GetMapping("/user/my-board/{id}") //개인 문의(유저 자기 자신 것)
    public ResponseEntity<BoardDto> getBoard(@PathVariable Long id){
        String name="test01@naver.com"; /*principal.getName();*/
        return new ResponseEntity<BoardDto>(boardService.getBoard(id,name),HttpStatus.OK);
    }

    @PostMapping("/user/new")    //유저 문의 create
    public ResponseEntity postBoard(@RequestBody BoardDto boardDto){
        String userId="test01@naver.com";

        Board board = boardService.createBoard(boardDto,userRepository.findByEmail(userId));
        return ResponseEntity.ok().build(); }

    @DeleteMapping("/user/my-board/{id}")
    public ResponseEntity<Object> deleteBoard(@PathVariable Long id){
        boardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin")      // 유저 전체문의 보기(관리자용)
    public ResponseEntity<List<Board>> getAdminBoardList(){
        List<Board> boardList = boardService.getBoardList();
        return new ResponseEntity<List<Board>>(boardList, HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")  //유저 개인문의 보기(관리자용)
    public ResponseEntity<BoardDto> getAdminBoard(@PathVariable Long id){
        String name="test01@naver.com";
        return new ResponseEntity<BoardDto>(boardService.getBoard(id,name),HttpStatus.OK);
    }

    @PostMapping("/admin/answer/{id}")  //문의 답장(관리자)
    public ResponseEntity postAnswer(@PathVariable Long id,@RequestParam String answer){
        String emailtest="test01@naver.com";
        Board board = boardService.insertAnswer(id, emailtest, answer);
        return new ResponseEntity<Board>(board,HttpStatus.OK);
    }



}
