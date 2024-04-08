package com.example.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.bookstore.domain.Book;
import com.example.bookstore.web.BookController;
import com.example.bookstore.web.CategoryContoller;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookstoreApplicationTests {

	@Autowired
	private BookController bc;

	@Autowired
	private CategoryContoller cc;

	// testaa että on injektoitu oikein/ ei oo tyhjä
	@Test
	void contextBLoads() throws Exception {
		assertThat(bc).isNotNull();
	}

	@Test
	void contextCLoads() throws Exception {
		assertThat(cc).isNotNull();
	}

}
