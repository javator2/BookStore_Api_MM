package com.example.BookStore.repository;

import com.example.BookStore.model.Book;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class InMemoryBookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setup(){
        bookRepository.deleteAll();

        Book book1 = new Book("1","Java","Author 1");
        Book book2 = new Book("2","Java and Spring","Author 1");
        Book book3 = new Book("3","JS in Action","Author 2");

        //BookRepository bookRepository = new InMemoryBookRepository();
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

    }

    @After
    public void cleanup(){
        bookRepository.deleteAll();
    }

    @Test
    public void shouldFindAllBooks() throws Exception {
        //given


        //when
        List<Book> books = bookRepository.findAll();

        //then
        assertThat(books.size()).isEqualTo(3);
        assertThat(books).isEqualTo(Lists.newArrayList(
                new Book("1","Java","Author 1"),
                new Book("2","Java and Spring","Author 1"),
                new Book("3","JS in Action","Author 2")
        ));

    }
}