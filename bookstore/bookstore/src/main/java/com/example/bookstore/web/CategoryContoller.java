package com.example.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class CategoryContoller {
    @Autowired
    private CategoryRepository crepository;

    @RequestMapping(value = "/categoriesjson", method = RequestMethod.GET)
    public @ResponseBody List<Category> categoryRest() {
        return (List<Category>) crepository.findAll();
    }

    @RequestMapping(value = ("/categories"), method = RequestMethod.GET)
    public String requestMethodName(Model model) {
        model.addAttribute("categoriess", crepository.findAll());
        return "categorylist";
    }

    @RequestMapping("/addcategoryform")
    public String addCategory(Model model) {
        model.addAttribute("ccategory", new Category());
        return "addcategory";
    }

    @RequestMapping(value = ("/savecategory"), method = RequestMethod.POST)
    public String saveCategory(@ModelAttribute("ccategory") Category ccategory) {
        crepository.save(ccategory);
        return "redirect:/categories";
    }
}
