package com.example.bookstore.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Book, Long> {

    List<Book> findByCategory(String category); // yks kategoria
    // List<Book> findByCategory(List<String> category); // jos kirja on jossai
    // kategoriassa useammasta

}
