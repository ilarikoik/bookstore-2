package com.example.bookstore.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {// book,long
    // crud eli create read update delete
    List<Book> findByAuthor(String author);
    // crudilla on metodeja valmiiks kuten findBy

}
