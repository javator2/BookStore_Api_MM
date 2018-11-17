package com.example.BookStore.model;

import java.util.List;

public class BookListing {

    private final List<Book> books;
    private final Integer numberOfBooks;



    public BookListing(List<Book> books, Integer numberOfBooks) {
        this.books = books;
        this.numberOfBooks = numberOfBooks;
    }

    public Integer getNumberOfBooks() {
        return numberOfBooks;
    }

    public List<Book> getBooks() {

        return books;
    }
    @Override
    public String toString() {
        return "BookListing{" +
                "books=" + books +
                ", numberOfBooks=" + numberOfBooks +
                '}';
    }

}
