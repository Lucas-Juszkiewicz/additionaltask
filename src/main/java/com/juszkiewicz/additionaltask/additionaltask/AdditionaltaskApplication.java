package com.juszkiewicz.additionaltask.additionaltask;

import com.juszkiewicz.additionaltask.additionaltask.entity.Book;
import com.juszkiewicz.additionaltask.additionaltask.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdditionaltaskApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(AdditionaltaskApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
		bookService.addBook(book1);

		Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 1960);
		bookService.addBook(book2);

		Book book3 = new Book("1984", "George Orwell", 1949);
		bookService.addBook(book3);

		Book book4 = new Book("Moby Dick", "Herman Melville", 1851);
		bookService.addBook(book4);

		Book book5 = new Book("Pride and Prejudice", "Jane Austen", 1813);
		bookService.addBook(book5);

		System.out.println("Books have been saved to the database at startup.");
	}
}
