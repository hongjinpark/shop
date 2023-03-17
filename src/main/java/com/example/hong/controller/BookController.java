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

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;


    @GetMapping
    public ResponseEntity getAllBook() {
        List<Book> result = bookMapper.getBook();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return bookService.getBook(id);
    }

    @PostMapping
    public Book save(@RequestBody @Valid BookDto bookDto) {
        return bookService.save(bookDto);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable int id , @RequestBody BookDto bookDto, BindingResult bindingResult) {
        return bookService.update(id, bookDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        bookService.delete(id);
    }

  /*@GetMapping
    public String imgUpload(MultipartFile multiPartFile) {
        return null;
    }*/

}
