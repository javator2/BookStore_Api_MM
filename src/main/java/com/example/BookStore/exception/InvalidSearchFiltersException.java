package com.example.BookStore.exception;

public class InvalidSearchFiltersException extends RuntimeException {
    public InvalidSearchFiltersException() {
        super("Invalid search filters esception. Pick ONE");
    }
}
