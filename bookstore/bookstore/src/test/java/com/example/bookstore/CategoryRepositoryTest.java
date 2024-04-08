package com.example.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import com.example.bookstore.web.BookController;

import jakarta.transaction.Transactional;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository crepo;

    @Test
    public void findByCateg() {
        Iterable<Category> cates = crepo.findAll();
        boolean comedy = false;
        boolean thriller = false;
        for (Category c : cates) {
            if (c.getCategory().equals("Comedy")) {
                comedy = true;
            }
            if (c.getCategory().equals("Thriller")) {
                thriller = true;
            }
            if (comedy && thriller) {
                break;
            }
        }
        assertThat(comedy && thriller).isTrue();
    }
}
