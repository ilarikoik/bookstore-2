package com.example.bookstore.domain;

import org.hibernate.mapping.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Book book;

    @Override
    public String toString() {
        return "Category [categoryid=" + categoryid + ", category=" + category + ", book=" + book + "]";
    }

}
