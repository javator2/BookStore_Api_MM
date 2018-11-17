package com.example.BookStore.service;

import com.example.BookStore.model.Book;
import com.example.BookStore.model.BookListing;
import com.example.BookStore.repository.BookRepository;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Before
    public void setup(){
        bookRepository.deleteAll();
    }

    @After
    public void clear(){
        bookRepository.deleteAll();
    }


    @Test
    public void getListing() {
    //given
        Book book1 = new Book("1","Java","Author 1");
        Book book2 = new Book("2","Java and Spring","Author 1");
        Book book3 = new Book("3","JS in Action","Author 2");

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
    //when
        BookListing bookListing = bookService.getListing();

    //then
        assertThat(bookListing.getNumberOfBooks()).isEqualTo(3);
        assertThat(bookListing.getBooks()).isEqualTo(
                Lists.newArrayList(
                    new Book("1","Java","Author 1"),
                    new Book("2","Java and Spring","Author 1"),
                    new Book("3","JS in Action","Author 2")                )
        );

    }
}