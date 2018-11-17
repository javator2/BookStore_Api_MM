package com.example.BookStore.model;

import java.util.Objects;

public class BookWithDetails {
    private final String title;
    private final String author;
    private final String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookWithDetails that = (BookWithDetails) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(author, that.author) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, author, description);
    }

    public String getTitle() {

        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public BookWithDetails(String title, String author, String description) {

        this.title = title;
        this.author = author;
        this.description = description;
    }
}
