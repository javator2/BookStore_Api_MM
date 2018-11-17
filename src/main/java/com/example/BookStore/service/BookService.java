package com.example.BookStore.service;


import com.example.BookStore.model.BookWithDetails;
import com.example.BookStore.model.SearchFilters;
import com.example.BookStore.exception.BookNotFoundException;
import com.example.BookStore.exception.InvalidSearchFiltersException;
import com.example.BookStore.model.Book;
import com.example.BookStore.model.BookListing;
import com.example.BookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private BookDescriptionClient bookClient;

    @Autowired
    public BookService(BookRepository bookRepository,
//                       @Qualifier("bookDescriptionLoripsum")
                               BookDescriptionClient bookClient) {
        this.bookRepository = bookRepository;
        this.bookClient = bookClient;
    }

    public BookListing getListing() {
        List<Book> bookList = bookRepository.findAll();
        return new BookListing(bookList, bookList.size());
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public Book getById(String bookId) {
//        return bookRepository.findById(bookId).orElse(new Book("marna","Kowalski"));
        return bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException(bookId));
    }

    public List<Book> getBtAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public void delete() {
        bookRepository.deleteAll();
    }

    public void deleteBookById(String bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        book.ifPresent(bookRepository::delete);
    }

    public BookListing getFilteredListing(SearchFilters searchFilters) {
        List<Book> bookList;
        if (searchFilters.hasAuthor() && searchFilters.hasTitle()) {
            //wyjątek
            throw new InvalidSearchFiltersException();
        }
        if (searchFilters.hasTitle()){
           bookList = bookRepository.findByTitle(searchFilters.getTitle());
        } else
        if (searchFilters.hasAuthor()) {
            bookList = bookRepository.findByAuthor(searchFilters.getAuthor());
        } else {
            bookList = bookRepository.findAll();
        }
        return new BookListing(bookList, bookList.size());
    }

    public BookWithDetails getBookWithDetails(String bookId) {
        Book book = getById(bookId);
        return new BookWithDetails(book.getTitle(), book.getAuthor(), bookClient.getDescription(bookId));
    }
}