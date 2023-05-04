package com.example.hong.controller;

import com.example.hong.config.auth.PrincipalDetail;
import com.example.hong.dto.BoardDto;
import com.example.hong.entity.Board;
import com.example.hong.repository.UserRepository;
import com.example.hong.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final UserRepository userRepository;

    @GetMapping("/user/my-board") //개인 문의 리스트(유저 자기 자신 것)
    public ResponseEntity<List<BoardDto>> getBoardList(@AuthenticationPrincipal PrincipalDetail principalDetail) {
        List<BoardDto> board = boardService.getBoardListOfUser(principalDetail.getEmail());
        return new ResponseEntity<List<BoardDto>>(board, HttpStatus.OK);}

    @GetMapping("/user/my-board/{id}") //개인 문의(유저 자기 자신 것)
    public ResponseEntity<BoardDto> getBoard(@PathVariable Long id,@AuthenticationPrincipal PrincipalDetail principalDetail){
        return new ResponseEntity<BoardDto>(boardService.getBoardOfUser(id,principalDetail.getEmail()),HttpStatus.OK);}

    @PostMapping("/user/new")    //유저 문의 create
    public ResponseEntity createBoard(@RequestBody BoardDto boardDto,@AuthenticationPrincipal PrincipalDetail principalDetail){
        Board board = boardService.createBoard(boardDto,userRepository.findByEmail(principalDetail.getEmail()));
        return ResponseEntity.ok().build(); }

    @DeleteMapping("/user/my-board/{id}")
    public ResponseEntity<Object> deleteBoard(@PathVariable Long id){
        boardService.deleteBoard(id);
        return ResponseEntity.ok().build(); }

    @GetMapping("/admin")      // 유저 전체문의 보기(관리자용)
    public ResponseEntity<List<BoardDto>> getAdminBoardList(@AuthenticationPrincipal PrincipalDetail principalDetail){
        List<BoardDto> boardList = boardService.getBoardListOfAdmin();
        return new ResponseEntity<List<BoardDto>>(boardList, HttpStatus.OK); }

    @GetMapping("/admin/{id}")  //유저 개인문의 보기(관리자용)
    public ResponseEntity<BoardDto> getAdminBoard(@PathVariable Long id,
                                                  @AuthenticationPrincipal PrincipalDetail principalDetail){
        return new ResponseEntity<BoardDto>(boardService.getBoardOfUser(id, principalDetail.getEmail()),HttpStatus.OK); }

    @PostMapping("/admin/{id}/answer")  //문의 답장(관리자)
    public ResponseEntity inputAnswer(@PathVariable Long id,
                                     @RequestParam String answer,
                                     @AuthenticationPrincipal PrincipalDetail principalDetail){
        Board board = boardService.inputAnswer(id, principalDetail.getEmail(), answer);
        return new ResponseEntity<Board>(board,HttpStatus.OK); }
}
