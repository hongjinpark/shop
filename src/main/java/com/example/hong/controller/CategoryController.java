package com.example.hong.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    // 카테고리 전체조회
    @GetMapping("/")
    public ResponseEntity<?> getCategory() {
        return null;
    }

    // 티어별로 카테고리 조회
    @GetMapping("/{tier}")
    public ResponseEntity<?> getCategoryTier() {
        return null;
    }

    // 카테고리 등록
    @PostMapping("/")
    public ResponseEntity<?> registCategory() {
        return null;
    }

    // 카테고리 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory() {
        return null;
    }
}
