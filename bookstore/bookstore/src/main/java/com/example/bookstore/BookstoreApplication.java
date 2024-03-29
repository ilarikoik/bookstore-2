package com.example.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import com.example.bookstore.domain.UserRepository;

import jakarta.transaction.Transactional;

import com.example.bookstore.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BookstoreApplication {

	// auttaa tulostamaan konsoliin --> parempi kun syso kun saa INFO WARNING JA
	// ERROR
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Autowired

	@Bean // ?
	// CommandLineRunner on rajapinta, jonka avulla voit suorittaa koodia
	// sovelluksen käynnistyksen jälkeen
	public CommandLineRunner bookDemo(CategoryRepository crepository, BookRepository repository,
			UserRepository urepository) {
		return (args) -> { // lambda ->
			log.info("save a couple of books"); // log laittaa aikaleiman siihe lisäyksii

			Category category1 = new Category("Comedy");
			Category category2 = new Category("Study");
			Category category3 = new Category("Thriller");
			Category category4 = new Category("Fiction");

			crepository.save(category1);
			crepository.save(category2);
			crepository.save(category3);
			crepository.save(category4);

			repository.save(new Book("eka", "Haaga", 2010, "91231", 29.49, category1));
			repository.save(new Book("toka", "Helia", 2002, "94912", 19.99, category2));
			repository.save(new Book("kolmas", "Koulu", 2024, "12412", 39.99, category3));
			repository.save(new Book("neljäs", "kirjailija", 2023, "8788323", 39.99, category4));

			log.info("fetch all Books"); // kaikki kirjat
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			log.info("haetaan kirjat by kirjalija");
			for (Book book : repository.findByAuthor("Haaga")) {
				log.info(book.toString());
			}
			User user1 = new User("user",
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin",
					"$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("kaikki kategoriat");
			for (Category category : crepository.findAll()) {
				// Accessing books within the transactional context
				log.info(category.toString());
				for (Book book : category.getBooks()) {
					log.info("Book: " + book.toString());
				}
			}

		};

	}
}
