package com.example.hong.controller;


import com.example.hong.dto.BookDto;
import com.example.hong.entity.Book;
import com.example.hong.mapper.BookMapper;
import com.example.hong.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;


    @GetMapping
    public ResponseEntity getAllBook() {
        Map<String, Object> result = bookMapper.getAllBook();
        return ResponseEntity.ok(result);
    }
    // book 조회
    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {

        return bookService.getBook(id);
    }

    // book 추가(insert)
    @PostMapping("my")
    public void insertBook(@RequestBody BookDto bookDto) {
        bookService.insertBook(bookDto);
    }

    @PostMapping
    public Book save(@RequestBody @Valid BookDto bookDto) {
        return bookService.save(bookDto);
    }

    // book 업데이트
    @PutMapping("/{id}")
    public Book update(@PathVariable int id , @RequestBody BookDto bookDto, BindingResult bindingResult) {
        return bookService.update(id, bookDto);
    }

    // book 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        bookService.delete(id);
    }

    @GetMapping("/mapper")
    public ResponseEntity getBook(@RequestParam String name) {
        Map<String, Object> result = bookService.getBookName(name);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/price")
    public ResponseEntity getPrice(@RequestParam String price) {
        List<Map<String, Object>> result = bookService.getBookPrice(price);
        return new ResponseEntity(result, HttpStatus.OK);
    }


}
