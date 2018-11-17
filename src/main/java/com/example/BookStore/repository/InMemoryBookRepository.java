package com.example.BookStore.repository;

import com.example.BookStore.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class InMemoryBookRepository implements BookRepository {

    private static List<Book> books = new ArrayList<>();
    private static final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public Book save(Book book) {
        Book preparedBook = prepare(book);
        books.add(preparedBook);
        return preparedBook;
    }



    private Book prepare(Book book) {
//        if (book.getId() != null) {
//            return book;
//        } else {
//            return new Book(
//                    String.valueOf(currentId.incrementAndGet()),
//                    book.getTitle(),
//                    book.getAuthor()
//            );
//      }
    return Optional.ofNullable(book.getId())
            .map((id) -> book)
            .orElse(new Book(String.valueOf(
                    currentId.incrementAndGet()),
                    book.getTitle(),
                    book.getAuthor()
            ));
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        books.clear();
        currentId.set(0);
    }

    @Override
    public Optional<Book> findById(String id) {
        return books.stream()
                .filter(book -> id.equals(book.getId()))
                .findFirst();
    }

    @Override
    public void delete(Book book) {
        books.remove(book);
    }

    public void deleteBookById(String id) {
        findById(id).ifPresent(book -> books.remove(book));
    }


}
