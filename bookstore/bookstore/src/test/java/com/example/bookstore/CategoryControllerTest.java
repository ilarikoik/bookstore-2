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
import com.example.bookstore.web.CategoryContoller;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    private CategoryContoller cate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", password = "user")
    public void categories() throws Exception {
        this.mockMvc.perform(get("/categories"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user")
    public void categoriesRest() throws Exception {
        this.mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user")
    public void categoriesAdd() throws Exception {
        this.mockMvc.perform(get("/addcategoryform"))
                .andExpect(status().isOk());
    }
}
