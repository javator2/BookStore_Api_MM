package com.example.BookStore.controller;


import com.example.BookStore.exception.BookNotFoundException;
import com.example.BookStore.exception.InvalidSearchFiltersException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookControlerHandler {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBookNotFoundException(BookNotFoundException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(InvalidSearchFiltersException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidSearchFiltersException(InvalidSearchFiltersException ex){
        return new ErrorResponse(ex.getMessage());
    }

}

