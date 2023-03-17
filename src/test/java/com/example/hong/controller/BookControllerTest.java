package com.example.hong.controller;

import com.example.hong.dto.BookDto;
import com.example.hong.entity.Book;
import com.example.hong.mapper.BookMapper;
import com.example.hong.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;


    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @After
    public void tearDown() throws Exception {
        bookRepository.findAll();
    }
    @Test
    public void postTest() throws Exception {
        //given
        String name = "kim";
        int price = 200;
        String publisher = "park";
        BookDto bookDto = BookDto.builder()
                .name(name)
                .price(price)
                .publisher(publisher)
                .build();

        String url = "http://localhost:" + 8090 + "/books";

        //when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bookDto)))
                .andExpect(status().isOk());

        //then
        List<Book> all = bookRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getPrice()).isEqualTo(price);
    }

    @Test
    @DisplayName("book 데이터 조회")
    public void getBook() throws Exception {

        //given
        List<Book> all = bookRepository.findAll();

        String url = "http://localhost:8090/books";

        //when
        mvc.perform(
                        get(url))
                        .andExpect(status().isOk())
                        .andDo(print());


        //then

    }

}