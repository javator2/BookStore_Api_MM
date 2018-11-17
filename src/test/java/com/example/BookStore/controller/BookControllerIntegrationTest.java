package com.example.BookStore.controller;

import com.example.BookStore.model.Book;
import com.example.BookStore.model.BookDto;
import com.example.BookStore.repository.BookRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class BookControllerIntegrationTest {

    @Value("${server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setup() {
        bookRepository.deleteAll();
    }

    @After
    public void tearDwon() {
        bookRepository.deleteAll();
    }

    @Test
    public void shouldReturnExistingBook() throws Exception{
        //given
        Book first = bookRepository.save(new Book("title1", "author 1"));
        Book second = bookRepository.save(new Book("title2", "author 2"));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_UTF8_VALUE);
        //when
        ResponseEntity<BookDto> book = restTemplate.exchange(
          prepareURL("/" + second.getId()),
          HttpMethod.GET,
          new HttpEntity<>(headers),
          BookDto.class
        );

        //then
        assertThat(book.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(book.getBody()).isEqualTo(new BookDto(second.getId(),"title2", "author 2"));
    }

    @Test
    public void shouldReturn404Response(){
        //given

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_UTF8_VALUE);
        //then
        ResponseEntity<BookDto> book = restTemplate.exchange(
                prepareURL("/not-existing-id"),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                BookDto.class
        );
        //when
        assertThat(book.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    public void shouldReturnFakeText() {
        //when
        ResponseEntity<String> result = restTemplate.getForEntity(prepareURL("/fake"), String.class);
        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo("some text");
    }

    private String prepareURL(String uri) {
        return "http://localhost:"+ port + "/books" + uri;
    }
}