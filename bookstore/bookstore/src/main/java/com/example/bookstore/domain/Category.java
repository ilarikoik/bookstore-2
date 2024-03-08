package com.example.bookstore.domain;

import java.util.List; // Correct import for List

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryid;
    private String category;

    @JsonIgnore
    // ignoraa book luokan ja estää ikuisen loopin tai jotai semmosta ( jos kokeilee
    // ilman tota niin tulee yks kategoria ja yks kirja vaa loopilla array arrayn
    // sisää)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.EAGER) // yhellä kategorialla monta
                                                                                          // kirjaa
    private List<Book> books; // Use java.util.List for generics

    public Category() {
    }

    public Category(String category) {
        this.category = category;
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return getCategory();
    }
}
