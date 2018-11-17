package com.example.BookStore;

import com.example.BookStore.model.Book;
import com.example.BookStore.repository.BookRepository;
import com.example.BookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BookStoreApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Book book1 = new Book( "Java", "Autor 1");
		Book book2 = new Book( "JavaScript", "Autor 2");
//		Book book3 = new Book( "JavaScript 2", "Autor 2");

//		BookRepository bookRepository = new InMemoryBookRepository();
//
		bookRepository.save(book1);
		bookRepository.save(book2);
//
//		System.out.println(bookService.getListing());

//		System.out.println(bookRepository.findAll());
//		Optional<Book> bookOptional = bookRepository.findById("2") ;

//		if (bookOptional.isPresent()){
//			System.out.println(bookOptional.get());
//		}
//		bookOptional.ifPresent(System.out::println);
	}

}
