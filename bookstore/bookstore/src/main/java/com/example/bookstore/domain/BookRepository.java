package com.example.bookstore.domain;

import java.util.List;
import java.util.function.IntPredicate;

import org.springframework.data.repository.CrudRepository;

// crudrepo tarjoaa valmiita metodeja luokalle
public interface BookRepository extends CrudRepository<Book, Long> {
    // crud eli create read update delete
    List<Book> findByAuthor(String author);

    void deleteByAuthor(String string);

    Book findByTitle(String string);

    // crudilla on metodeja valmiiks kuten findBy, deleteBy, count...

}
