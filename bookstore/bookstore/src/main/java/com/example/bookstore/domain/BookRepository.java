package com.example.bookstore.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

// crudrepo tarjoaa valmiita metodeja luokalle
public interface BookRepository extends CrudRepository<Book, Long> {
    // crud eli create read update delete
    List<Book> findByAuthor(String author);

    // crudilla on metodeja valmiiks kuten findBy, deleteBy, count...

}
