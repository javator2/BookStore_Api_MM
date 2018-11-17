package com.example.BookStore.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {

    private final String id;
    private final String title;
    private final String author;

    @JsonCreator
    public BookDto(
            @JsonProperty("id") String id,
            @JsonProperty("title") String title,
            @JsonProperty("author") String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public static BookDto fromDomain(Book book) {
        return new BookDto(book.getId(),book.getTitle(), book.getAuthor());
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Book toDomain() {
        return new Book(id, title, author);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id) &&
                Objects.equals(title, bookDto.title) &&
                Objects.equals(author, bookDto.author);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, author);
    }
}
