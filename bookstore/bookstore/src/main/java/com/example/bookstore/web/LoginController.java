package com.example.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

import com.example.bookstore.domain.BookRepository;

@Controller
public class LoginController {

    @Autowired
    private BookRepository repository;

    @RequestMapping(value = { ("/login") }, method = RequestMethod.GET)
    public String login() {
        return "loginpage";
    }

    /* @PreAuthorize("hasRole('ADMIN')") */

    /*
     * @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
     * public String deleteStudent(@PathVariable("id") Long bookId, Model model) {
     * repository.deleteById(bookId);
     * return "redirect:../allbooks";
     * }
     */

}
