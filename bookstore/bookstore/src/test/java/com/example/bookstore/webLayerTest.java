package com.example.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class webLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", password = "user")
    public void testDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/allbooks")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("all books")));
    }

    @Test
    @WithMockUser(username = "user", password = "user")
    public void testAdd() throws Exception {
        this.mockMvc.perform(get("/addbook")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("add book")));
    }

}
