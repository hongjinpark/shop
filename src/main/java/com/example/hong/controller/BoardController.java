package com.example.hong.controller;

import com.example.hong.entity.Board;
import com.example.hong.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

//    @GetMapping("/admin")
//    public List<Board> getBoard(){
//
//    }

}
