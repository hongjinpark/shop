package com.example.hong.controller;


import ch.qos.logback.classic.util.LogbackMDCAdapter;
import com.example.hong.dto.CategoryDto;
import com.example.hong.entity.Category;
import com.example.hong.entity.Item;
import com.example.hong.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryMapper categoryMapper;
    Map<String, Object> resultMap = new HashMap<>();
    // 카테고리 추가
    @PostMapping("/new")
    public ResponseEntity createCategory(@RequestBody CategoryDto categoryDto){
        categoryMapper.createCategory(categoryDto);
        return ResponseEntity.ok().build();
    }

    // 카테고리 상세조회
    @GetMapping("/list/{tier}")
    public ResponseEntity<?> getCategory(@PathVariable String tier){
        List<Category> categorys = categoryMapper.getCategory(tier);
        resultMap.put("categorys", categorys);

        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }
    // insert category_id to item table
    @PostMapping("/insert")
    public ResponseEntity insertCategory(@RequestBody CategoryDto categoryDto){
        categoryMapper.insertCategory(categoryDto);
        return ResponseEntity.ok().build();
    }
}
