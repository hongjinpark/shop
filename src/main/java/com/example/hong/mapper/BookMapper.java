package com.example.hong.mapper;

import com.example.hong.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BookMapper {

    Map<String, Object> getAllBook();

    Map<String, Object> getBookName(String name);

    void insertBook(Book book);

    void delete(int id);

    List<Map<String, Object>> getBookPrice(String price);
}
