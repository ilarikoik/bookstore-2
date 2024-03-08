package com.example.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

@Controller // kertoo springille että luokka tekee HTTP pyyntöjä/käskyjä
public class BookController {
    @Autowired // perjaatteessa importoi repon luokan käyttöön
    private BookRepository repository;

    @Autowired
    private CategoryRepository crepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showIndex(/* Model model */) {
        return "index";
    }

    @RequestMapping(value = "/booksjson", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookRest() {
        return (List<Book>) repository.findAll();
    }

    @RequestMapping(value = "/booksjson/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> bookRestById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @RequestMapping(value = { ("/allbooks") }, method = RequestMethod.GET)
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    @RequestMapping("/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book()); // lähettää uuden tyhjän olion sivulle ?
        model.addAttribute("categoriat", crepository.findAll());
        return "addbook";
    }

    @RequestMapping(value = ("/save"), method = RequestMethod.POST)
    public String saveBook(Book book) {
        repository.save(book);
        return "redirect:allbooks";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", repository.findById(id));
        return "edit";
    }

    @RequestMapping(value = ("/update"), method = RequestMethod.POST)
    public String updateBook(Book book) {
        repository.save(book);
        return "redirect:/allbooks";
    }

    @RequestMapping(value = ("/delete/{id}"), method = RequestMethod.GET)
    public String requestMethodName(@PathVariable("id") Long id, Model model) {
        repository.deleteById(id);
        return "redirect:../allbooks";
    }

}
