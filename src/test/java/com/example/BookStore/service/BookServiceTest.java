package com.example.BookStore.service;

import com.example.BookStore.model.Book;
import com.example.BookStore.model.BookListing;
import com.example.BookStore.repository.BookRepository;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


public class BookServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldReturnListing() {
    //given
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        when(bookRepository.findAll()).thenReturn(
                Lists.newArrayList(
                        new Book("1","Title1", "Author1"),
                        new Book("2","Title2", "Author1")
                )
        );

        BookDescriptionClient bookClient = null;
        BookService bookService = new BookService(bookRepository, bookClient);

        //when
        BookListing bookListing = bookService.getListing();

        //then
        assertThat(bookListing.getNumberOfBooks()).isEqualTo(2);



    }
}