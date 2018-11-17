package com.example.BookStore.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service("bookDescriptionDummy")
@Profile("dev")
public class BookDescriptionDummyClient implements BookDescriptionClient {
    @Override
    public String getDescription(String bookId) {
        return "super książka";
    }
}
