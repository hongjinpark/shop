package com.example.hong.service;


import com.example.hong.dto.BookDto;
import com.example.hong.entity.Book;
import com.example.hong.mapper.BookMapper;
import com.example.hong.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    /**
     *
     * @param bookDto
     * @return bookDto.toEntity()
     */
    @Transactional
    public Book save(BookDto bookDto) {

        return bookRepository.save(bookDto.toEntity());
    }

    /**
     *
     * @param id : book_id
     * @param bookDto : name, price, publisher, title
     * @return book
     */
    @Transactional
    public Book update(int id, BookDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));
        book.update(bookDto.getName(), bookDto.getTitle(), bookDto.getPrice(), bookDto.getPublisher());
        return book;
    }

    public Book getBook(int id) {
        return bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    //Mybatis

    public Map<String, Object> getBookName(String name) {

        return bookMapper.getBookName(name);
    }
}
