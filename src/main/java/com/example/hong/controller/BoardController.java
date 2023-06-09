package com.example.hong.controller;

import com.example.hong.config.auth.PrincipalDetail;
import com.example.hong.dto.BoardDto;
import com.example.hong.entity.Board;
import com.example.hong.mapper.BoardMapper;
import com.example.hong.repository.UserRepository;
import com.example.hong.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final UserRepository userRepository;
    private final BoardMapper boardMapper;

    Map<String, Object> resultMap = new HashMap<String, Object>();

    @GetMapping("/user/my-board")
    public ResponseEntity<List<BoardDto>> getBoardList(@AuthenticationPrincipal PrincipalDetail principalDetail) {
        log.info("==============================={}",principalDetail.getEmail());
        List<BoardDto> board = boardService.getBoardListOfUser(principalDetail);
        log.info("==============================={}",board);
        return new ResponseEntity<List<BoardDto>>(board, HttpStatus.OK);}

    @GetMapping("/user/my-board/{id}")
    public ResponseEntity<BoardDto> getBoard(@PathVariable Long id,@AuthenticationPrincipal PrincipalDetail principalDetail){
        return new ResponseEntity<BoardDto>(boardService.getBoardOfUser(id,principalDetail.getEmail()),HttpStatus.OK);}

    @PostMapping("/user/new")
    public ResponseEntity createBoard(@RequestBody BoardDto boardDto,@AuthenticationPrincipal PrincipalDetail principalDetail){
        Board board = boardService.createBoard(boardDto,userRepository.findByEmail(principalDetail.getEmail()));
        return ResponseEntity.ok().build(); }

    @DeleteMapping("/user/my-board/{id}")
    public ResponseEntity<Object> deleteBoard(@PathVariable Long id){
        boardService.deleteBoard(id);
        return ResponseEntity.ok().build(); }

    @GetMapping("/admin")
    public ResponseEntity<List<BoardDto>> getAdminBoardList(@AuthenticationPrincipal PrincipalDetail principalDetail){
        List<BoardDto> boardList = boardService.getBoardListOfAdmin();
        return new ResponseEntity<List<BoardDto>>(boardList, HttpStatus.OK); }

    @GetMapping("/admin/{id}")
    public ResponseEntity<BoardDto> getAdminBoard(@PathVariable Long id,
                                                  @AuthenticationPrincipal PrincipalDetail principalDetail){
        return new ResponseEntity<BoardDto>(boardService.getBoardOfAdmin(id, principalDetail.getEmail()),HttpStatus.OK); }

    @PostMapping("/admin/{id}/answer")
    public ResponseEntity inputAnswer(@PathVariable Long id,
                                      @RequestParam String answer,
                                      @AuthenticationPrincipal PrincipalDetail principalDetail) throws Exception{
        boardService.inputAnswer(id, principalDetail.getEmail(), answer);
        return new ResponseEntity<Board>(HttpStatus.OK); }

    @GetMapping("/admin/search/{title}")
    public ResponseEntity<?> searchBoard(@PathVariable String title){
        List<Board> boards = boardMapper.searchBoard(title);
        resultMap.put("boards",boards);

        return new ResponseEntity<>(boards,HttpStatus.OK);
    }
}
