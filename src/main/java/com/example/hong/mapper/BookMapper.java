package com.example.hong.mapper;

import com.example.hong.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BookMapper {

    List<Book> getAllBook();

    Map<String, Object> getBookName(String name);
}
