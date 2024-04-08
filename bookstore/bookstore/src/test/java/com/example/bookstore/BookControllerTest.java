package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.web.BookController;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookController bc;

    @Test
    public void controllerNotEmpty() throws Exception {
        assertThat(bc).isNotNull();
    }

    @Test
    @WithMockUser(username = "user", password = "user")
    public void endPoint() throws Exception {
        this.mockMvc.perform(get("/allbooks"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user")
    public void endPointAddBook() throws Exception {
        this.mockMvc.perform(get("/addbook"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user")
    public void endPointRest() throws Exception {
        this.mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user")
    public void endPointRestId() throws Exception {
        this.mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk());
    }

}
