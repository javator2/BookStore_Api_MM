package com.example.BookStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service("bookDescriptionLoripsum")
@Profile("default")
public class BookDescriptionLoripsumClient implements BookDescriptionClient {


    public RestOperations getRestTemplate() {
        return restTemplate;
    }

    private final RestOperations restTemplate;
    private final String address;



    @Autowired
    public BookDescriptionLoripsumClient(RestOperations restTemplate, @Value("${loripsum.url}") String address) {
        this.restTemplate = restTemplate;
        this.address = address;
    }

    @Override
    public String getDescription(String bookId) {
        return restTemplate.getForObject(address, String.class);
    }
}
