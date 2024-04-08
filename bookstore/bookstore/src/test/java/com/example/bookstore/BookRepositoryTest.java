package com.example.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository brepo;

    @Autowired
    CategoryRepository crepo;

    // haetaa kirja nimellä reposta
    // katotaa että sillä kirjailijalla on vähitää yks kirja
    // ekan kirjan nimi pitäs olla .isEqualTo("eka");
    @Test
    public void findByName() {
        List<Book> author = brepo.findByAuthor("Haaga");
        assertThat(author).hasSize(1);
        assertThat(author.get(0).getTitle()).isEqualTo("eka");
    }

    // uus kirja ja katotaa että se lisätää oikein repoon , eli sille pitäs löytyy
    // id
    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void createBook() {
        /*
         * Category cate = new Category("fiction");
         * crepo.save(cate);
         */
        Category catee = crepo.findOneByCategory("Fiction");
        Book book = new Book("testi1", "ilari", 2024, "1231sxf", 99.9, catee);
        brepo.save(book);
        assertThat(book.getId()).isNotNull();
        assertThat(brepo.findByAuthor("ilari")).isNotNull();
    }

    @Transactional // ?? ilma ei toimi
    /*
     * @Transactional on Spring Frameworkissä on annotaatio, jota käytetään
     * ilmoittamaan, että annotoidun metodin tulisi suorittaa toimet yhtenä
     * transaktionaalisena kokonaisuutena. Transaktio tarkoittaa joukkoa toimia,
     * jotka joko suoritetaan kokonaan tai peruutetaan kokonaan, jos jokin niistä
     * epäonnistuu. Tämä varmistaa tietokannan eheyden ja konsistenssin.
     */
    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void deleteBooka() throws Exception {
        Category catee = crepo.findOneByCategory("Fiction");
        Book book = new Book("testi1", "ilmari", 2024, "1231sxf", 99.9, catee);
        brepo.save(book);
        assertThat(book.getId()).isNotNull();

        brepo.deleteByAuthor("ilmari");
        List<Book> booksByAuthor = brepo.findByAuthor("ilmari");
        assertThat(booksByAuthor).isEmpty();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void findBooka() throws Exception {
        Book book = new Book("testi1", "Haaga", 2024, "1231sxf", 99.9, crepo.findOneByCategory("Fiction"));
        brepo.save(book);
        List<Book> list = brepo.findByAuthor("Haaga");
        assertThat(list).hasSize(2); // repoo on tallennettu jo yks "Haaga" author
    }

}
