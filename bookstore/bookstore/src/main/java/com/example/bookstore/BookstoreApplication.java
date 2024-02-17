package com.example.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);

	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> { // lambda ->
			log.info("save a couple of books"); // log laittaa aikaleiman siihe lisäyksii
			repository.save(new Book("eka", "Haaga", 2010, "91231", 29.49));
			repository.save(new Book("toka", "Helia", 2002, "94912", 19.99));
			repository.save(new Book("kolmas", "Koulu", 2024, "12412", 39.99));

			log.info("fetch all Books"); // kaikki kirjat
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			log.info("haetaan kirjat by kirjalija");
			for (Book book : repository.findByAuthor("Haaga")) {
				log.info(book.toString());
			}

		};
	}

}
